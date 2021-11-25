using System;
using System.Data;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;
using Oracle.DataAccess;
using Oracle.DataAccess.Client;
using Oracle.DataAccess.Types;

namespace WindowsFormsApp3
{
    static class Program
    {
        /// <summary>
        /// 해당 애플리케이션의 주 진입점입니다.
        /// </summary>
        [STAThread]
        static void Main()
        {/*
            string connect_info = "DATA SOURCE = xe; User Id = TeamProject; password = 1234;";

            OracleConnection conn = new OracleConnection(connect_info);
            OracleCommand command = new OracleCommand("blindFunc", conn);
            command.CommandType = CommandType.StoredProcedure;

            OracleParameter output = new OracleParameter();
            output.Direction = ParameterDirection.ReturnValue;
            command.Parameters.Add(output);

            OracleParameter input = new OracleParameter("v_string", OracleDbType.Int32);
            input.Direction = ParameterDirection.Input;
            input.Value = 7900;
            command.Parameters.Add(input);

            try
            {
                conn.Open();
                command.ExecuteNonQuery();
                conn.Close();

                Console.WriteLine("{0}", output.Value);
                Console.ReadLine();
            }
            catch (OracleException e) {
                Console.WriteLine(e.Number);
                Console.WriteLine(e.Message);
                Console.WriteLine();
            }*/

            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            Application.Run(new Form1());
        }
    }
}
