using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Oracle.DataAccess.Client;

namespace WindowsFormsApp3
{
    public partial class Form1 : Form
    {
        string connect_info = "DATA SOURCE = xe; User Id = TeamProject; password = 1234;";

        OracleConnection conn;
        OracleCommand comm;
        OracleDataAdapter adt;
        DataSet data = new DataSet();
        Random rand = new Random();

        public Form1()
        {
            InitializeComponent();
            for (int i = 0; i < 23; i++)
            {
                int a = rand.Next(1111, 9999);
                int b = rand.Next(1111, 9999);
                string sql1 = "update STUDENTTBL set tel = " + "('010-" + a + "-" + b + "') " +
                                "where rownumber = " + (i + 1);
                conn = new OracleConnection(connect_info);
                conn.Open();
                comm = new OracleCommand(sql1, conn);
                comm.ExecuteNonQuery();
            }
        }


        private void Form1_Load(object sender, EventArgs e)
        {
            comm = new OracleCommand(Func(), conn);
            comm.ExecuteNonQueryAsync();


        }


        private void button1_Click(object sender, EventArgs e)
        {
            if (textBox1.TextLength < 1)
            {
                DataSet data = new DataSet();
                string sql = "select blindFunction(NAME) as \"이름\", GENDER as \"성별\", TEL as \"전화번호\", Email from STUDENTTBL;";
                conn = new OracleConnection(connect_info);
                conn.Open();
                adt = new OracleDataAdapter(sql, conn);
                adt.Fill(data);
                dataGridView1.DataSource = data.Tables[0];
                //MessageBox.Show("학생 정보 출력");
                conn.Close();
            }
            else
            {
                string userName = string.Format(textBox1.Text);
                string namesql = "where name = " + "'" + userName + "'";

                DataSet data = new DataSet();

                string sql = "select blindFunction(NAME) as \"이름\", GENDER as \"성별\", TEL as \"전화번호\", Email from STUDENTTBL " + namesql;


                conn = new OracleConnection(connect_info);
                conn.Open();
                adt = new OracleDataAdapter(sql, conn);
                adt.Fill(data);
                dataGridView1.DataSource = data.Tables[0];
                conn.Close();
            }
            
        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
           
        }

        private void button2_Click_1(object sender, EventArgs e)
        {
            if (textBox1.TextLength < 1)
            {
                DataSet data = new DataSet();
                string sql = "select blindFunction(S.NAME) as \"회원\", S.EMAIL as \"Email\", S.TEL as \"전화번호\", " +
                            "J.PASS as \"수료 여부\", J.PROCESS as \"진도율\", J.TASK as \"과제\", J.TOTAL as \"총점\" from STUDENTTBL S " +
                                "inner join JAVATBL J " +
                                    "on S.name = J.name ";

                //자바는 과제가 없어서 추가하지 않음

                conn = new OracleConnection(connect_info);
                conn.Open();
                adt = new OracleDataAdapter(sql, conn);
                adt.Fill(data);
                dataGridView1.DataSource = data.Tables[0];
            }
            else
            {
                string userName = string.Format(textBox1.Text);
                string namesql = "where s.name = " + "'" + userName + "'";

                DataSet data = new DataSet();

                string sql = "select blindFunction(S.NAME) as \"회원\", S.EMAIL as \"Email\", S.TEL as \"전화번호\", " +
                                "J.PASS \"수료 여부\", J.PROCESS as \"진도율\", J.TASK as \"과제\", J.TOTAL as \"총점\" from STUDENTTBL S " +
                                    "inner join JAVATBL J " +
                                        "on S.name = J.name " +
                                        namesql;

                conn = new OracleConnection(connect_info);
                conn.Open();
                adt = new OracleDataAdapter(sql, conn);
                adt.Fill(data);
                dataGridView1.DataSource = data.Tables[0];
                conn.Close();
            }
        }

        private void button3_Click(object sender, EventArgs e)
        {

            if (textBox1.TextLength < 1)
            {
                DataSet data = new DataSet();
                string sql = "select blindFunction(S.NAME) as \"회원\", S.EMAIL as \"Email\", S.TEL as \"전화번호\", " +
                                "C.PASS as \"수료 여부\", C.PROCESS as \"진도율\", C.TASK as \"과제\", C.TOTAL as \"총점\" from STUDENTTBL S " +
                                "inner join CSHARPTBL C " +
                                    "on S.name = C.name ";

                conn = new OracleConnection(connect_info);
                conn.Open();
                adt = new OracleDataAdapter(sql, conn);
                adt.Fill(data);
                dataGridView1.DataSource = data.Tables[0];
                conn.Close();
            }
            else
            {
                string userName = string.Format(textBox1.Text);
                string namesql = "where s.name = " + "'" + userName + "'";


                DataSet data = new DataSet();
                string sql = "select blindFunction(S.NAME) as \"회원\", S.EMAIL as \"Email\", S.TEL as \"전화번호\", " +
                                "C.PASS as \"수료 여부\", C.PROCESS as \"진도율\", C.TASK as \"과제\", C.TOTAL as \"총점\" from STUDENTTBL S " +
                                    "inner join CSHARPTBL C " +
                                        "on S.name = C.name " +
                                        namesql;

                conn = new OracleConnection(connect_info);
                conn.Open();
                adt = new OracleDataAdapter(sql, conn);
                adt.Fill(data);
                dataGridView1.DataSource = data.Tables[0];
                conn.Close();
            }

        }

        private void button4_Click(object sender, EventArgs e)
        {
            if (textBox1.TextLength < 1)
            {
                DataSet data = new DataSet();
                string sql = "select blindFunction(S.NAME) as \"회원\", S.EMAIL as \"Email\", S.TEL as \"전화번호\", " +
                                "P.PASS as \"수료 여부\", P.PROCESS as \"진도율\", P.TASK as \"과제\", P.TOTAL as \"총점\" from STUDENTTBL S " +
                                    "inner join PYTHONTABLE P " +
                                        "on S.name = P.name ";

                conn = new OracleConnection(connect_info);
                conn.Open();
                adt = new OracleDataAdapter(sql, conn);
                adt.Fill(data);
                dataGridView1.DataSource = data.Tables[0];
                conn.Close();
            }
            else
            {
                string userName = string.Format(textBox1.Text);
                string namesql = "where s.name = " + "'" + userName + "'";

                DataSet data = new DataSet();
                string sql = "select blindFunction(S.NAME) as \"회원\", S.EMAIL as \"Email\", S.TEL as \"전화번호\", " +
                                "P.PASS as \"수료 여부\", P.PROCESS as \"진도율\", P.TASK as \"과제\", P.TOTAL as \"총점\" from STUDENTTBL S " +
                                    "inner join PYTHONTABLE P " +
                                        "on S.name = P.name " +
                                        namesql;

                conn = new OracleConnection(connect_info);
                conn.Open();
                adt = new OracleDataAdapter(sql, conn);
                adt.Fill(data);
                dataGridView1.DataSource = data.Tables[0];
                conn.Close();
            }

        }

        public string Func()
        {
            String BlindFunc = "create or replace fuction blindFunction(uString NCHAR) " +
                                "return nchar " +
                                "as " +
                                "v_string NCHAR(20) := ''; " +
                                "begin " +
                                "if uString = '-' then " +
                                "return v_string; " +
                                "end if; " +
                                "if substr(uString, 1, 1) = '0' then " +
                                "v_string := concat( substr(uString, 1, 8), '-xxxx'); " +
                                "else " +
                                "v_string := concat( substr(uString, 1, 1), '00'); " +
                                "end if; " +
                                "return v_string; " +
                                "end blindFunction;";

            return BlindFunc;
        }

        private void button5_Click(object sender, EventArgs e)
        {
            MessageBox.Show("본 프로그램의 데이터는 5월 21일 기준입니다.");
        }

        private void button6_Click(object sender, EventArgs e)
        {
            Application.ExitThread();
            Environment.Exit(0);
        }
    }
}
