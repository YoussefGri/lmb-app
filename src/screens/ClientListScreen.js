// src/screens/ClientListScreen.js
import React, { useState } from 'react';
import { View, Text, TextInput, FlatList, StyleSheet, TouchableOpacity } from 'react-native';

const ClientListScreen = ({ navigation }) => {
  const [search, setSearch] = useState('');
  const [clients, setClients] = useState([
    { id: '1', firstName: 'Paul', lastName: 'Michel', phone: '06 12 34 56 78', email: 'michel.paul@gmail.com', address: '836 rue du Mas de Verchant', postalCode: '34000', city: 'Montpellier' },
    { id: '2', firstName: 'Mathieu', lastName: 'Bompart', phone: '06 11 22 33 44', email: 'bompart.mathieu@gmail.com', address: '123 rue de la Paix', postalCode: '75000', city: 'Paris' },
    // Ajouter d'autres clients ici
  ]);

  const updateClient = (updatedClient) => {
    setClients(clients.map(client => client.id === updatedClient.id ? updatedClient : client));
  };

  const filteredClients = clients.filter(client =>
    client.firstName.toLowerCase().includes(search.toLowerCase()) ||
    client.lastName.toLowerCase().includes(search.toLowerCase())
  );

  return (
    <View style={styles.container}>
      <TextInput
        style={styles.searchInput}
        placeholder="Rechercher"
        value={search}
        onChangeText={setSearch}
      />
      <FlatList
        data={filteredClients}
        keyExtractor={item => item.id}
        renderItem={({ item }) => (
          <TouchableOpacity style={styles.clientItem} onPress={() => navigation.navigate('ClientProfile', { client: item, updateClient })}>
            <View style={styles.avatar}>
              <Text style={styles.avatarText}>
                {item.firstName.charAt(0)}{item.lastName.charAt(0)}
              </Text>
            </View>
            <View style={styles.clientInfo}>
              <Text style={styles.clientLastName}>{item.lastName}</Text>
              <Text style={styles.clientFirstName}>{item.firstName}</Text>
            </View>
          </TouchableOpacity>
        )}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 10,
    backgroundColor: '#fff',
  },
  searchInput: {
    height: 40,
    borderColor: '#ccc',
    borderWidth: 1,
    borderRadius: 5,
    paddingHorizontal: 10,
    marginBottom: 10,
  },
  clientItem: {
    flexDirection: 'row',
    alignItems: 'center',
    padding: 10,
    borderBottomWidth: 1,
    borderBottomColor: '#eee',
  },
  avatar: {
    width: 40,
    height: 40,
    borderRadius: 20,
    backgroundColor: '#ddd',
    justifyContent: 'center',
    alignItems: 'center',
    marginRight: 10,
  },
  avatarText: {
    color: '#fff',
    fontWeight: 'bold',
  },
  clientInfo: {
    flex: 1,
  },
  clientLastName: {
    fontWeight: 'bold',
  },
  clientFirstName: {
    color: '#777',
  },
});

export default ClientListScreen;
