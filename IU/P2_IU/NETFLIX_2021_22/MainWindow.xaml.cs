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
using System.Windows.Media.Animation;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace NETFLIX_2021_22
{
    /// <summary>
    /// Lógica de interacción para MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
            Loger.Content = new Login();
            Loger.NavigationUIVisibility = NavigationUIVisibility.Hidden;
            
        }

        private void B_im1_Click(object sender, RoutedEventArgs e)
        {
            Viewer.Content = new trailer1();
            Viewer.NavigationUIVisibility = NavigationUIVisibility.Hidden;
        }


        private void Cerrar_sesion_Click(object sender, RoutedEventArgs e)
        {
            Loger.Content = new Login();
            Loger.NavigationUIVisibility = NavigationUIVisibility.Hidden;
        }

        private void B_img2_Click(object sender, RoutedEventArgs e)
        {
            Viewer.Content = new trailer2();
            Viewer.NavigationUIVisibility = NavigationUIVisibility.Hidden;
        }

        private void B_img3_Click(object sender, RoutedEventArgs e)
        {
            Viewer.Content = new trailer3();
            Viewer.NavigationUIVisibility = NavigationUIVisibility.Hidden;
        }

        private void B_img4_Click(object sender, RoutedEventArgs e)
        {
            Viewer.Content = new trailer4();
            Viewer.NavigationUIVisibility = NavigationUIVisibility.Hidden;

        }

        private void B_img5_Click(object sender, RoutedEventArgs e)
        {
            Viewer.Content = new trailer5();
            Viewer.NavigationUIVisibility = NavigationUIVisibility.Hidden;
        }

        private void B_img6_Click(object sender, RoutedEventArgs e)
        {
            Viewer.Content = new trailer6();
            Viewer.NavigationUIVisibility = NavigationUIVisibility.Hidden;
        }

        private void B_im1Copy_Click(object sender, RoutedEventArgs e)
        {
            Viewer.Content = new trailer1();
            Viewer.NavigationUIVisibility = NavigationUIVisibility.Hidden;
        }

        private void B_img2Copy_Click(object sender, RoutedEventArgs e)
        {
            Viewer.Content = new intro_blackcover();
            Viewer.NavigationUIVisibility = NavigationUIVisibility.Hidden;
        }

        private void B_img3Copy_Click(object sender, RoutedEventArgs e)
        {
            Viewer.Content = new trailer3();
            Viewer.NavigationUIVisibility = NavigationUIVisibility.Hidden;
        }

        private void B_img4Copy_Click(object sender, RoutedEventArgs e)
        {
            Viewer.Content = new trailer4();
            Viewer.NavigationUIVisibility = NavigationUIVisibility.Hidden;
        }

        private void B_img5Copy_Click(object sender, RoutedEventArgs e)
        {
            Viewer.Content = new trailer5();
            Viewer.NavigationUIVisibility = NavigationUIVisibility.Hidden;
        }

        private void B_img6Copy_Click(object sender, RoutedEventArgs e)
        {
            Viewer.Content = new trailer6();
            Viewer.NavigationUIVisibility = NavigationUIVisibility.Hidden;
        }

        private void B_blackcover_Click(object sender, RoutedEventArgs e)
        {
            Viewer.Content = new intro_blackcover();
            Viewer.NavigationUIVisibility = NavigationUIVisibility.Hidden;
            
        }

        private void Cerrar_Netflix_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }

        private void B_cerrarViewer_Click(object sender, RoutedEventArgs e)
        {
            Viewer.GoBack();
        }
    }
}
