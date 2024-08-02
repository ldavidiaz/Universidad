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
    /// Lógica de interacción para trailer3.xaml
    /// </summary>
    public partial class trailer3 : Page
    {
        public trailer3()
        {
            InitializeComponent();
        }

        private void b_pause_Click(object sender, RoutedEventArgs e)
        {
            IntroFyF.Pause();
        }

        private void B_stop_Click(object sender, RoutedEventArgs e)
        {
            IntroFyF.Stop();
        }

        private void B_play_Click(object sender, RoutedEventArgs e)
        {
            IntroFyF.Play();
        }

        private void Play1_Click(object sender, RoutedEventArgs e)
        {
            Storyboard story3 = (Storyboard)this.FindResource("hide_play1"); ;
            story3.Begin();
            IntroFyF.Play();
        }

        private void Audio0_Click(object sender, RoutedEventArgs e)
        {
            sound.Value = 0;
        }
    }
}
