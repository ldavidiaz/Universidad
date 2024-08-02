import React, { useState } from 'react';
import { View, StyleSheet, Switch, Text } from 'react-native';
import { txtAcceptTerms } from '../constants/strings-es';
const ToggleApp = ({onToggle}) => {
    const [isEnabled, setIsEnabled] = useState(false);

    const toggleSwitch = () => {
      const newState = !isEnabled;
      setIsEnabled(newState);
      if (onToggle) {
        onToggle(newState);
      }
    };

    return (
        <View style={styles.container}>
            <Switch value={isEnabled} style={styles.toggle} onValueChange={toggleSwitch} />
            
            <Text style={{alignContent: 'center'}}>{txtAcceptTerms}</Text>
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        //borderWidth: 1,
        width: '100%',
        maxHeight: '10%',
        display: 'flex',
        //flex:1,
        flexDirection: 'row',
        justifyContent: 'start',
        alignItems: 'center',
        /* marginTop: 10,
        paddingBottom: 10,
        paddingLeft: 15,  */
    },
    toggle:{
        marginLeft: 15,
    }
});

export default ToggleApp;