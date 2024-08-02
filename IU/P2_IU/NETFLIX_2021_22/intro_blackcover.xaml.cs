﻿using System;
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
    /// Lógica de interacción para intro_blackcover.xaml
    /// </summary>
    public partial class intro_blackcover : Page
    {
        public intro_blackcover()
        {
            InitializeComponent();
        }

        private void B_play_Click(object sender, RoutedEventArgs e)
        {
            introBlackCover.Play();
        }

        private void B_pause_Click(object sender, RoutedEventArgs e)
        {
            introBlackCover.Pause();
        }

        private void B_stop_Click(object sender, RoutedEventArgs e)
        {
            introBlackCover.Stop();
        }

        private void Play1_Click(object sender, RoutedEventArgs e)
        {
            Storyboard story0 = (Storyboard)this.FindResource("hide_play1"); ;
            story0.Begin();
            introBlackCover.Play();
        }

        private void Audio0_Click(object sender, RoutedEventArgs e)
        {
            sound.Value = 0;
        }
    }
}
