import React, { useRef, useEffect } from 'react';
import { View,Animated, Easing,StyleSheet } from 'react-native';
import { library } from '@fortawesome/fontawesome-svg-core';
import { faReact } from '@fortawesome/free-brands-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-native-fontawesome';
import { height, width } from '@fortawesome/free-brands-svg-icons/fa42Group';
library.add(faReact);
const LogoApp = () => {
    const spinValue = useRef(new Animated.Value(0)).current;

    useEffect(() => {
      Animated.loop(
        Animated.timing(
          spinValue,
          {
            toValue: 1,
            duration: 3000,
            easing: Easing.linear,
            useNativeDriver: true,
          }
        )
      ).start();
    }, [spinValue]);
  
    const spin = spinValue.interpolate({
      inputRange: [0, 1],
      outputRange: ['0deg', '360deg']
    });

    return (
      <View style={styles.container}>
            <Animated.View style={{ transform: [{ rotate: spin }],height:108,width:108,}}>
                <FontAwesomeIcon icon={faReact} size={108} color="blue" />
            </Animated.View>
      </View>
    );
};
const styles = StyleSheet.create({
    container: {
      
      /* borderWidth: 1, */
      width: '100%',   
      maxHeight: '25%',
      //flex: 2,
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'flex-end',

    },
});
export default LogoApp;