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
    /// Lógica de interacción para trailer6.xaml
    /// </summary>
    public partial class trailer6 : Page
    {
        public trailer6()
        {
            InitializeComponent();
        }

        private void B_pause_Click(object sender, RoutedEventArgs e)
        {
            IntroDragonBall.Pause();
        }

        private void B_stop_Click(object sender, RoutedEventArgs e)
        {
            IntroDragonBall.Stop();
        }

        private void B_play_Click(object sender, RoutedEventArgs e)
        {
            IntroDragonBall.Play();
        }

        private void Play1_Click(object sender, RoutedEventArgs e)
        {
            Storyboard story1 = (Storyboard)this.FindResource("hide_play1"); ;
            story1.Begin();
            IntroDragonBall.Play();
            
        }

        private void Au1_Click(object sender, RoutedEventArgs e)
        {
            Sound.Value = 0;
        }
    }
}
