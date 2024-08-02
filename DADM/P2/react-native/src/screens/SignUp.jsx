import React, {useState} from 'react';
import { View, StyleSheet,Text,KeyboardAvoidingView,Platform } from 'react-native';
import BtnSignInApp from '../components/BtnSignInApp';
import ScrollViewApp from '../components/ScrollViewApp';
import FormApp from '../components/FormApp';
import LogoApp from '../components/LogoApp';
import ToggleApp from '../components/ToggleApp';
import { titlePractica2 } from '../constants/strings-es'; 
/* Hay que importar
npm install @fortawesome/fontawesome-svg-core
npm install --save @fortawesome/free-solid-svg-icons
npm install --save @fortawesome/react-native-fontawesome
*/
const SignUp = () => {
    const [isSwitchEnabled, setIsSwitchEnabled] = useState(false);
    const [username,setUsername] = useState('');
    const [password,setPassword] = useState('');


    const handleSwitchToggle = (isEnabled) => {
        setIsSwitchEnabled(isEnabled);
    };
    
    return (
        <View style={styles.container}>
            <LogoApp />
            <Text style={[{fontSize:24,maxHeight:'10%',marginTop:8,marginBottom:12}]}>{titlePractica2}</Text>
            <FormApp
                username={username} 
                setUsername={setUsername} 
                password={password} 
                setPassword={setPassword} 
                isSwitchEnabled={isSwitchEnabled} 
                setIsSwitchEnabled={setIsSwitchEnabled}       
            />
            <ScrollViewApp />
            <ToggleApp  onToggle={handleSwitchToggle}/>
            <BtnSignInApp  isSwitchEnabled={isSwitchEnabled} username={username} password={password}/>
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        width: '100%',
        height: '100%',
        display: 'flex',
        flex:1,
        flexDirection: 'column',
        justifyContent: 'center',
        alignItems: 'center',
      //paddingTop: 200,
    }
});

export default SignUp;