namespace WindowsFormsApp1
{
    partial class Form1
    {
        /// <summary>
        /// Wymagana zmienna projektanta.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Wyczyść wszystkie używane zasoby.
        /// </summary>
        /// <param name="disposing">prawda, jeżeli zarządzane zasoby powinny zostać zlikwidowane; Fałsz w przeciwnym wypadku.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Kod generowany przez Projektanta formularzy systemu Windows

        /// <summary>
        /// Metoda wymagana do obsługi projektanta — nie należy modyfikować
        /// jej zawartości w edytorze kodu.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.dataGridView1 = new System.Windows.Forms.DataGridView();
            this.tableAdapterManager = new WindowsFormsApp1.DaneDataSetTableAdapters.TableAdapterManager();
            this.tableAdapterManager1 = new WindowsFormsApp1.DaneFinalLaptopTableAdapters.TableAdapterManager();
            this.daneWaluty = new WindowsFormsApp1.DaneWaluty();
            this.currencyDataBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.currencyDataTableAdapter = new WindowsFormsApp1.DaneWalutyTableAdapters.CurrencyDataTableAdapter();
            this.tableAdapterManager2 = new WindowsFormsApp1.DaneWalutyTableAdapters.TableAdapterManager();
            this.currencyDataIDDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.amountDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.exchangeRateDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.convertedAmountDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.daneWaluty)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.currencyDataBindingSource)).BeginInit();
            this.SuspendLayout();
            // 
            // dataGridView1
            // 
            this.dataGridView1.AutoGenerateColumns = false;
            this.dataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView1.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.currencyDataIDDataGridViewTextBoxColumn,
            this.amountDataGridViewTextBoxColumn,
            this.exchangeRateDataGridViewTextBoxColumn,
            this.convertedAmountDataGridViewTextBoxColumn});
            this.dataGridView1.DataSource = this.currencyDataBindingSource;
            this.dataGridView1.Location = new System.Drawing.Point(16, 15);
            this.dataGridView1.Margin = new System.Windows.Forms.Padding(4);
            this.dataGridView1.Name = "dataGridView1";
            this.dataGridView1.RowHeadersWidth = 51;
            this.dataGridView1.Size = new System.Drawing.Size(1035, 524);
            this.dataGridView1.TabIndex = 0;
            // 
            // tableAdapterManager
            // 
            this.tableAdapterManager.BackupDataSetBeforeUpdate = false;
            this.tableAdapterManager.Connection = null;
            this.tableAdapterManager.CurrencyDataTableAdapter = null;
            this.tableAdapterManager.UpdateOrder = WindowsFormsApp1.DaneDataSetTableAdapters.TableAdapterManager.UpdateOrderOption.InsertUpdateDelete;
            // 
            // tableAdapterManager1
            // 
            this.tableAdapterManager1.BackupDataSetBeforeUpdate = false;
            this.tableAdapterManager1.UpdateOrder = WindowsFormsApp1.DaneFinalLaptopTableAdapters.TableAdapterManager.UpdateOrderOption.InsertUpdateDelete;
            // 
            // daneWaluty
            // 
            this.daneWaluty.DataSetName = "DaneWaluty";
            this.daneWaluty.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // currencyDataBindingSource
            // 
            this.currencyDataBindingSource.DataMember = "CurrencyData";
            this.currencyDataBindingSource.DataSource = this.daneWaluty;
            // 
            // currencyDataTableAdapter
            // 
            this.currencyDataTableAdapter.ClearBeforeFill = true;
            // 
            // tableAdapterManager2
            // 
            this.tableAdapterManager2.BackupDataSetBeforeUpdate = false;
            this.tableAdapterManager2.CurrencyDataTableAdapter = this.currencyDataTableAdapter;
            this.tableAdapterManager2.UpdateOrder = WindowsFormsApp1.DaneWalutyTableAdapters.TableAdapterManager.UpdateOrderOption.InsertUpdateDelete;
            // 
            // currencyDataIDDataGridViewTextBoxColumn
            // 
            this.currencyDataIDDataGridViewTextBoxColumn.DataPropertyName = "CurrencyDataID";
            this.currencyDataIDDataGridViewTextBoxColumn.HeaderText = "CurrencyDataID";
            this.currencyDataIDDataGridViewTextBoxColumn.MinimumWidth = 6;
            this.currencyDataIDDataGridViewTextBoxColumn.Name = "currencyDataIDDataGridViewTextBoxColumn";
            this.currencyDataIDDataGridViewTextBoxColumn.ReadOnly = true;
            this.currencyDataIDDataGridViewTextBoxColumn.Width = 125;
            // 
            // amountDataGridViewTextBoxColumn
            // 
            this.amountDataGridViewTextBoxColumn.DataPropertyName = "Amount";
            this.amountDataGridViewTextBoxColumn.HeaderText = "Amount";
            this.amountDataGridViewTextBoxColumn.MinimumWidth = 6;
            this.amountDataGridViewTextBoxColumn.Name = "amountDataGridViewTextBoxColumn";
            this.amountDataGridViewTextBoxColumn.Width = 125;
            // 
            // exchangeRateDataGridViewTextBoxColumn
            // 
            this.exchangeRateDataGridViewTextBoxColumn.DataPropertyName = "ExchangeRate";
            this.exchangeRateDataGridViewTextBoxColumn.HeaderText = "ExchangeRate";
            this.exchangeRateDataGridViewTextBoxColumn.MinimumWidth = 6;
            this.exchangeRateDataGridViewTextBoxColumn.Name = "exchangeRateDataGridViewTextBoxColumn";
            this.exchangeRateDataGridViewTextBoxColumn.Width = 125;
            // 
            // convertedAmountDataGridViewTextBoxColumn
            // 
            this.convertedAmountDataGridViewTextBoxColumn.DataPropertyName = "ConvertedAmount";
            this.convertedAmountDataGridViewTextBoxColumn.HeaderText = "ConvertedAmount";
            this.convertedAmountDataGridViewTextBoxColumn.MinimumWidth = 6;
            this.convertedAmountDataGridViewTextBoxColumn.Name = "convertedAmountDataGridViewTextBoxColumn";
            this.convertedAmountDataGridViewTextBoxColumn.Width = 125;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1067, 554);
            this.Controls.Add(this.dataGridView1);
            this.Margin = new System.Windows.Forms.Padding(4);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.daneWaluty)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.currencyDataBindingSource)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataGridView dataGridView1;
        private DaneDataSetTableAdapters.TableAdapterManager tableAdapterManager;
        private DaneFinalLaptopTableAdapters.TableAdapterManager tableAdapterManager1;
        private DaneWaluty daneWaluty;
        private System.Windows.Forms.BindingSource currencyDataBindingSource;
        private DaneWalutyTableAdapters.CurrencyDataTableAdapter currencyDataTableAdapter;
        private DaneWalutyTableAdapters.TableAdapterManager tableAdapterManager2;
        private System.Windows.Forms.DataGridViewTextBoxColumn currencyDataIDDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn amountDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn exchangeRateDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn convertedAmountDataGridViewTextBoxColumn;
    }
}

