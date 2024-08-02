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
    /// Lógica de interacción para trailer2.xaml
    /// </summary>
    public partial class trailer2 : Page
    {
        public trailer2()
        {
            InitializeComponent();
        }

        private void B_pause_Click(object sender, RoutedEventArgs e)
        {
            IntroSpiderman.Pause();
        }

        private void B_stop_Click(object sender, RoutedEventArgs e)
        {
            IntroSpiderman.Stop();
        }

        private void B_play_Click(object sender, RoutedEventArgs e)
        {
            IntroSpiderman.Play();
        }

        private void Play1_Click(object sender, RoutedEventArgs e)
        {
            Storyboard story2 = (Storyboard)this.FindResource("hide_play1"); ;
            story2.Begin();
            IntroSpiderman.Play();
        }

        private void Audio0_Click(object sender, RoutedEventArgs e)
        {
            sound.Value = 0;
        }
    }
}
