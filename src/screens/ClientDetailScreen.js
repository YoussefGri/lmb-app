import React, { useState } from 'react';
import { View, Text, TextInput, Button, StyleSheet } from 'react-native';

const ClientDetailScreen = ({ route, navigation }) => {
  const { client, updateClient } = route.params;
  const [firstName, setFirstName] = useState(client.firstName);
  const [lastName, setLastName] = useState(client.lastName);
  const [phone, setPhone] = useState(client.phone);
  const [email, setEmail] = useState(client.email);
  const [address, setAddress] = useState(client.address);
  const [postalCode, setPostalCode] = useState(client.postalCode);
  const [city, setCity] = useState(client.city);

  const handleSave = () => {
    const updatedClient = { ...client, firstName, lastName, phone, email, address, postalCode, city };
    updateClient(updatedClient);
    navigation.goBack();
  };

  return (
    <View style={styles.container}>
      <View style={styles.fieldContainer}>
        <Text style={styles.label}>Prénom & NOM</Text>
        <TextInput style={styles.input} value={firstName} onChangeText={setFirstName} />
        <TextInput style={styles.input} value={lastName} onChangeText={setLastName} />
      </View>
      <View style={styles.fieldContainer}>
        <Text style={styles.label}>Téléphone</Text>
        <TextInput style={styles.input} value={phone} onChangeText={setPhone} />
      </View>
      <View style={styles.fieldContainer}>
        <Text style={styles.label}>Email</Text>
        <TextInput style={styles.input} value={email} onChangeText={setEmail} />
      </View>
      <View style={styles.fieldContainer}>
        <Text style={styles.label}>Adresse</Text>
        <TextInput style={styles.input} value={address} onChangeText={setAddress} />
      </View>
      <View style={styles.fieldContainer}>
        <Text style={styles.label}>Code postal</Text>
        <TextInput style={styles.input} value={postalCode} onChangeText={setPostalCode} />
      </View>
      <View style={styles.fieldContainer}>
        <Text style={styles.label}>Ville</Text>
        <TextInput style={styles.input} value={city} onChangeText={setCity} />
      </View>
      <Button title="Enregistrer" onPress={handleSave} />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
    backgroundColor: '#fff',
  },
  fieldContainer: {
    marginBottom: 15,
  },
  label: {
    color: '#007BFF',
    fontWeight: 'bold',
  },
  input: {
    borderBottomWidth: 1,
    borderBottomColor: '#ccc',
    padding: 5,
    marginBottom: 10,
  },
});

export default ClientDetailScreen;