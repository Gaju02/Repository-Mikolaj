using System;
using System.Collections.Generic;
using System.Data;
using System.IO;
using System.Linq;
using System.Windows.Forms;

namespace WindowsFormsApp1
{
    public partial class FormjSON : Form
    {
        DataSet dataSet;
        DataTable dataTable;

        public FormjSON()
        {
            InitializeComponent();
            CsvToDataSet();
        }

        public void CsvToDataSet()
        {
            string filePath = "curr.csv";

            try
            {
                using (StreamReader reader = new StreamReader(filePath))
                {
                    dataSet = new DataSet();
                    dataTable = new DataTable("Currency");

                    bool isFirstRow = true;

                    while (!reader.EndOfStream)
                    {
                        string line = reader.ReadLine();
                        string[] fields = line.Split(',');

                        if (isFirstRow)
                        {
                           
                            foreach (var field in fields)
                            {
                                dataTable.Columns.Add(field);
                            }

                            isFirstRow = false;
                        }
                        else
                        {
                            string formattedLine = string.Join(";", fields.Select(field => field.Replace(",", ";")));
                            dataTable.Rows.Add(formattedLine);
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error reading CSV file: " + ex.Message);
                return;
            }

            dataSet = new DataSet();
            dataSet.Tables.Add(dataTable);

            this.bindingSource1.DataSource = dataSet;
            this.bindingSource1.DataMember = "Currency";
            this.dataGridView1.DataSource = this.bindingSource1;
        }
    }
}

