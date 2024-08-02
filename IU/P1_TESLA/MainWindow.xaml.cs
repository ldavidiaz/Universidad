using System;
using System.Windows;
using System.Windows.Media.Animation;

namespace PRACTICA1_TESLA
{
    /// <summary>
    /// Lógica de interacción para MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {


        public MainWindow()
        {
            InitializeComponent();
        }



        private void Cerrar_App_Click_1(object sender, RoutedEventArgs e)
        {
            this.Close();
        }

        private void EntrarCuenta_Click(object sender, System.Windows.RoutedEventArgs e)
        {
            string userName = Usuario.Text;
            string User = this.Usuario.Text;
            string Password = this.Pass.Password;


            if (User.CompareTo("Alumno") == 0 && Password.CompareTo("1234") == 0)
            {
                MessageBox.Show("Bienvenido @" + userName);
                
            }
            else
            {
                MessageBox.Show("Usuario o contraseña incorrecta");
                Usuario.Clear();
                Pass.Clear();
            }


        }


    }   
}
