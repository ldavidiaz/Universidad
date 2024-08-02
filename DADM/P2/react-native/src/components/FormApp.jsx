import React from 'react';
import { View, StyleSheet,Text,TextInput } from 'react-native';
import { lblEmail, lblPassword,placeholderEmail,placeholderPassword } from '../constants/strings-es';
const FormApp = ( { username, setUsername, password, setPassword }) => {
    return (
        <View style={styles.container}>
            <View style={styles.containerInputs}>
                <Text style={styles.label}>{lblEmail}</Text>
                <TextInput style={styles.input}
                type="text" 
                placeholder={placeholderEmail}
                value={username}
                onChangeText={setUsername} 
                />
            </View>
            <View style={styles.containerInputs}>
                <Text style={styles.label}>{lblPassword}</Text>
                <TextInput type="password" 
                placeholder={placeholderPassword}
                style={styles.input}
                value={password}
                onChangeText={setPassword}
                secureTextEntry
                />
            </View>
            
        </View>
    );
};
const styles = StyleSheet.create({
    container: {
      //borderWidth: 1,
      maxHeight: '28%',
      width: '100%', // Ajusta el ancho según sea necesario
      display: 'flex',
      //flex:2,
      flexDirection: 'column',
      justifyContent: 'start',
      alignItems: 'center',
      gap: 1,
    },
    containerInputs: {
        //borderWidth: 1,
        width: '100%',
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'center',
        alignItems: 'flex-start',
        paddingRight:40,
        paddingLeft:20,
    },
    input: {
        width: '100%',
        fontSize: 20,
        height: 40,
        backgroundColor: 333333,
        color: 'black',
        borderRadius: 5,
        paddingLeft: 10,
        //paddingBottom: 10,
    },
    /* firstInput: {
        marginLeft: 60,
    }, */
    label: {
        fontSize: 18,
        paddingBottom: 5,
    }
});
export default FormApp;
