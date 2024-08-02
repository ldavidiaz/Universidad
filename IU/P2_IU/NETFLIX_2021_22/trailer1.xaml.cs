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
    /// Lógica de interacción para trailer1.xaml
    /// </summary>
    public partial class trailer1 : Page
    {
        public trailer1()
        {
            InitializeComponent();
        }

        private void B_pause_Click(object sender, RoutedEventArgs e)
        {
            IntroTruman.Pause();
        }

        private void B_stop_Click(object sender, RoutedEventArgs e)
        {
            IntroTruman.Stop();
        }

        private void B_play_Click(object sender, RoutedEventArgs e)
        {
            IntroTruman.Play();
        }

        private void Play1_Click(object sender, RoutedEventArgs e)
        {
            Storyboard story1 = (Storyboard)this.FindResource("hide_play1"); ;
            story1.Begin();
            IntroTruman.Play();
        }

        private void Audio0_Click(object sender, RoutedEventArgs e)
        {
            sound.Value = 0;
        }
    }
}
