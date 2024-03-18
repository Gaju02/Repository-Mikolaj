using System;
using System.Windows.Forms;
using System.Net;
using System.IO;
using System.Linq;
using Newtonsoft.Json;
using System.Data.SqlClient;
namespace WindowsFormsApp1
{
    public partial class Currency : Form
    {
        public const string defaultUrl = "https://www.money.pl/pieniadze/nbp/srednie/";
        public string currencyFormat;
        public string currency;
        public decimal convertedAmount;
        public decimal exchangeRate;
        public decimal amount;
        public string rowFirst = "amount;currency;convertedAmount;currency";
        public string fileName = "curr.csv";
        public Currency()
        {
            InitializeComponent();
        }
        public void CurrencyForm_Load(object sender, EventArgs e)
        {
            
            webBrowser1.Url = new Uri(defaultUrl);
        }

        public void button1_Click(object sender, EventArgs e)
        {
            webBrowser1.Navigate(new Uri(defaultUrl));
        }

        public void button2_Click(object sender, EventArgs e)
        {
            if (!decimal.TryParse(textBox1.Text, out decimal amount))
            {
                MessageBox.Show("Nieprawidłowa liczba.", "Błąd", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            currency = Dolar.Checked ? "USD" : "EUR";

            
            exchangeRate = GetExchangeRate(currency);

            convertedAmount = amount * exchangeRate;

            label2.Text = $"Wynik: {convertedAmount} PLN (kurs: {exchangeRate})";
        }
        private decimal GetExchangeRate(string currencyCode)
        {
            
            DateTime yesterday = DateTime.Now.AddDays(-1);

            
            string formattedDate = yesterday.ToString("yyyy-MM-dd");

            
            string apiUrl = $"http://api.nbp.pl/api/exchangerates/rates/A/{currencyCode}/{formattedDate}/";

            
            try
            {
                using (WebClient client = new WebClient())
                {
                    string jsonResult = client.DownloadString(apiUrl);
                    Console.WriteLine(jsonResult);

                    
                    dynamic jsonData = JsonConvert.DeserializeObject(jsonResult);

                    
                    if (jsonData != null && jsonData.rates != null && jsonData.rates.Count > 0)
                    {
                        decimal rate = jsonData.rates[0].mid;
                        return rate;
                    }
                    else
                    {
                        MessageBox.Show("Błąd podczas pobierania kursu walutowego z API NBP: Brak danych kursu w odpowiedzi.", "Błąd", MessageBoxButtons.OK, MessageBoxIcon.Error);
                        return 0;
                    }
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Błąd podczas pobierania kursu walutowego z API NBP: {ex.Message}", "Błąd", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return 0;
            }
        }

        private void button4_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrEmpty(currency))
            {
                MessageBox.Show("Najpierw oblicz wynik.", "Błąd", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }

            string connectionString = "Data Source=DESKTOP-S69LRE7;Initial Catalog=Dane;Integrated Security=True;";

            string insertQuery = "INSERT INTO CurrencyData (Amount,ExchangeRate,ConvertedAmount) VALUES (@Amount, @ExchangeRate, @ConvertedAmount)";
            decimal finalAmount = decimal.Parse(textBox1.Text);
            using (SqlConnection connection = new SqlConnection(connectionString))
            {
                connection.Open();

                using (SqlCommand cmd = new SqlCommand(insertQuery, connection))
                {
                    
                    cmd.Parameters.AddWithValue("@Amount", finalAmount);
                    cmd.Parameters.AddWithValue("@ExchangeRate", exchangeRate);
                    cmd.Parameters.AddWithValue("@ConvertedAmount", convertedAmount);
                    try
                    {
                        
                        int rowsAffected = cmd.ExecuteNonQuery();

                        if (rowsAffected > 0)
                        {
                            MessageBox.Show("Rekord został dodany do bazy danych.", "Sukces", MessageBoxButtons.OK, MessageBoxIcon.Information);
                        }
                        else
                        {
                            MessageBox.Show("Błąd podczas dodawania rekordu do bazy danych.", "Błąd", MessageBoxButtons.OK, MessageBoxIcon.Error);
                        }
                    }
                    catch (Exception ex)
                    {
                        MessageBox.Show($"Błąd podczas dodawania rekordu do bazy danych: {ex.Message}", "Błąd", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    }
                }
            }
        }

        public void button3_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrEmpty(currency))
            {
                MessageBox.Show("Najpierw oblicz wynik.", "Błąd", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            currencyFormat = radioButton1.Checked ? "CSV" : "TXT";

            if (currencyFormat == "CSV")
            {
                string newRow = $"{int.Parse(textBox1.Text)};{exchangeRate};{convertedAmount}"+$";{currency}";
                SaveRowToFile(newRow);
            }

            if (currencyFormat == "TXT")
            {
                string newRow = $"{int.Parse(textBox1.Text)};{exchangeRate};{convertedAmount}";
                File.WriteAllText("C:\\Users\\Mikołaj\\Desktop\\Gajewski_20180_ProjektC#\\Projekt\\core\\WindowsFormsApp1\\WindowsFormsApp1\\bin\\Debug\\tekst.txt", newRow);
            }

            MessageBox.Show($"Dane zostały zapisane w formacie {currencyFormat}.", "Zapisano", MessageBoxButtons.OK, MessageBoxIcon.Information);
        }

        public void SaveRowToFile(string rowToFile)
        {
            StreamWriter streamWriter;
            bool isFile = false;
            isFile = File.Exists(fileName);
            streamWriter = File.AppendText(fileName);
            if (!isFile)
            {
                streamWriter.WriteLine(rowFirst);
            }

            
            string formattedRow = rowToFile.Replace(',', '.');

            streamWriter.WriteLine(formattedRow);
            streamWriter.Close();
        }

        private void label3_Click(object sender, EventArgs e)
        {

        }

        private void radioButton2_CheckedChanged(object sender, EventArgs e)
        {

        }
    }
}
