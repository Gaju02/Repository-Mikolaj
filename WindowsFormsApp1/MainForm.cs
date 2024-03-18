using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsFormsApp1
{
    public partial class MainForm : Form
    {
        public MainForm()
        {
            InitializeComponent();
        }

        private void jSONToolStripMenuItem_Click(object sender, EventArgs e)
        {

        }

        private void menuStrip1_ItemClicked(object sender, ToolStripItemClickedEventArgs e)
        {
            Form f=null;
            switch (e.ClickedItem.Text)
            {
                case "SQLServer":
                    f = new Form1();
                    f.BackColor = Color.Aqua;
                    break;
                case "ShowCSV":
                    f = new FormjSON();
                    f.BackColor = Color.CadetBlue;
                    break;
                case "Currency":
                    f = new Currency();
                    f.BackColor = Color.Aquamarine;
                    break;


            }       
            f.MdiParent = this;
            f.Show();
        }

        private void sQLServerToolStripMenuItem_Click(object sender, EventArgs e)
        {

        }
    }
}
