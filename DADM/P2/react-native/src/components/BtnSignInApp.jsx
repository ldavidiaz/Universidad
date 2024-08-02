import React, {useState} from 'react';
import { Button, StyleSheet, Alert,View } from 'react-native';   
import { btnCreateAccount,alertIncompleteData,alertWelcome } from '../constants/strings-es';
const BtnSignInApp = ({ isSwitchEnabled , username, password}) => {

    const handleButtonPress = () => {
        //verificar datos de entrada
        if(username === '' || password === ''){
            Alert.alert(`${alertIncompleteData}`);
            return;
        }
        else{
            //verificar si el usuario existe
            //si existe, mostrar mensaje de error
            //si no existe, crear usuario
            Alert.alert(`${alertWelcome} ${username.toUpperCase()}`);
        }
      };

    return (
        <View style={styles.container}>
            <Button style={styles.button} 
                title={btnCreateAccount}
                onPress={handleButtonPress}
                disabled={!isSwitchEnabled}
            />
        </View>

    );
};

const styles = StyleSheet.create({
    container:{
       /*  borderWidth: 2, */
        width: '100%',
        maxHeight: '7%',
        display: 'flex',
        justifyContent: 'start',
        //flex: 1,
    },
    button: {
        backgroundColor: '#4CAF50',
        padding: 10,
        borderRadius: 5,
    },
    text: {
        color: '#fff',
        textAlign: 'center',
        fontSize:16,
    },
});

export default BtnSignInApp;