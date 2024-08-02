import React from 'react';
import {StyleSheet,Text,ScrollView,View } from 'react-native';
import { txtTrminosYcondiciones } from '../constants/strings-es';
const ScrollViewApp = () => {
    return (
      <View style={styles.container}>
              <ScrollView 
              style={styles.scrollView}
              contentContainerStyle={styles.contentContainer}
              showsVerticalScrollIndicator={true}
              >
                <Text 
                style={{fontSize:14,
                  textAlign:'justify',
                  backgroundColor:555,
                  paddingHorizontal: 10,
                  paddingVertical: 10,
                  borderRadius: 5}}>{txtTrminosYcondiciones}</Text>
                
              </ScrollView>
      </View>

    );
};
const styles = StyleSheet.create({
  container:{
    //borderWidth: 1,
    width: '100%',
    maxHeight: '20%',
    //flex:1,
    display: 'flex',
    alignItems: 'flex-start',
    justifyContent: 'flex-start',
    padding: 10,
  },
    scrollView: {
      width: '100%', // Ajusta el ancho según sea necesario
      /* borderTopWidth: 1, */
      /* borderColor: '333', */
      shadowColor: '#000', // Color de la sombra
      shadowOffset: { width: 0, height: 2 }, // Desplazamiento de la sombra
      shadowOpacity: 0.5, // Opacidad de la sombra
      shadowRadius: 2, // Radio de la sombra
    },
    contentContainer: {
/*       paddingHorizontal: 20,
      paddingVertical: 10, */
    },
  });
export default ScrollViewApp;