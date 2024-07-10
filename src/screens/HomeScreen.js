import React, { useState } from 'react';
import { View, Text, TextInput, Button, StyleSheet } from 'react-native';
import { authenticate, getClients } from '../../services/api';

const HomeScreen = ({ navigation }) => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = async () => {
    const token = await authenticate(username, password);
    if (token) {
      const clients = await getClients(token);
      navigation.navigate('ClientList', { clients });
    } else {
      alert('Authentication failed');
    }
  };

  return (
    <View style={styles.container}>
      <Text>Username:</Text>
      <TextInput
        style={styles.input}
        value={username}
        onChangeText={setUsername}
      />
      <Text>Password:</Text>
      <TextInput
        style={styles.input}
        value={password}
        onChangeText={setPassword}
        secureTextEntry
      />
      <Button title="Login" onPress={handleLogin} />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    padding: 20,
  },
  input: {
    height: 40,
    borderColor: 'gray',
    borderWidth: 1,
    marginBottom: 12,
    paddingLeft: 8,
  },
});

export default HomeScreen;