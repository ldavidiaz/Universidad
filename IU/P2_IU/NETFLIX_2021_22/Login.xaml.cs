using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace NETFLIX_2021_22
{
    /// <summary>
    /// Lógica de interacción para Login.xaml
    /// </summary>
    public partial class Login : Page
    {
        

        public Login()
        {
            InitializeComponent();
            
        }

        private void EntrarApp_Click(object sender, RoutedEventArgs e)
        {
            String username = User.Text;
            PasswordBox pass = Password;
            if (username == "" || pass.Password == "")
            {
                MessageBox.Show("Inserte el nombre de usuario o el Password");
                User.Focus();
                return;
            }
            if (username == "Alumno" && pass.Password.Equals("1234"))
            {
                MessageBox.Show("Bienvenido " + username);
                User.Clear();
                pass.Clear();
                gridPage.Visibility = Visibility.Collapsed;

            }
            else
            {
                MessageBox.Show("Error. Usuario o contraseña incorrectos");
                User.Clear();
                pass.Clear();
            }
        }
    }
}
