/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  Button
} from 'react-native';
import BackgroundCheckModule from 'react-native-background-check';

const onShowToast = () => {
  BackgroundCheckModule.alert("Hey! It works!");
};

const onOpenSettings = () => {
  BackgroundCheckModule.openSettings();
};

const onGotoBackground = () => {
  BackgroundCheckModule.bringApptoBackground();
};

const onIsLocked = () => {
  BackgroundCheckModule.isLocked((msg) => {
    console.log(msg);
  }, (isLocked) => {
    console.log(isLocked);
  });
};

export default class Example extends Component {
  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>
          Welcome to React Native!
        </Text>
        <Button title='Show Toast' onPress={onShowToast}/>
        <Text style={styles.instructions}>
          To get started, edit index.android.js
        </Text>
        <Button title='Open Settings' onPress={onOpenSettings}/>
        <Text style={styles.instructions}>
          Double tap R on your keyboard to reload,{'\n'}
          Shake or press menu button for dev menu
        </Text>
        <Button title='Go to Background' onPress={onGotoBackground}/>
        <Button title='Is Locked ?' onPress={onIsLocked}/>

      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});

AppRegistry.registerComponent('Example', () => Example);
