// src/screens/ClientProfileScreen.js
import React from 'react';
import { View, Text, StyleSheet, TouchableOpacity } from 'react-native';
import Icon from 'react-native-vector-icons/FontAwesome';

const ClientProfileScreen = ({ route, navigation }) => {
  const { client, updateClient } = route.params;

  React.useLayoutEffect(() => {
    navigation.setOptions({
      headerRight: () => (
        <TouchableOpacity onPress={() => navigation.navigate('ClientDetail', { client, updateClient })}>
          <Text style={styles.editButton}>Éditer</Text>
        </TouchableOpacity>
      ),
    });
  }, [navigation, client, updateClient]);

  return (
    <View style={styles.container}>
      <View style={styles.header}>
        <View style={styles.avatar}>
          <Text style={styles.avatarText}>{client.firstName.charAt(0)}{client.lastName.charAt(0)}</Text>
        </View>
        <Text style={styles.name}>{client.lastName}</Text>
        <Text style={styles.name}>{client.firstName}</Text>
      </View>
      <View style={styles.detailContainer}>
        <Text style={styles.label}>Téléphone</Text>
        <View style={styles.detailRow}>
          <Text style={styles.value}>{client.phone}</Text>
          <Icon name="phone" size={20} color="#007BFF" />
        </View>
      </View>
      <View style={styles.detailContainer}>
        <Text style={styles.label}>Email</Text>
        <View style={styles.detailRow}>
          <Text style={styles.value}>{client.email}</Text>
          <Icon name="envelope" size={20} color="#007BFF" />
        </View>
      </View>
      <View style={styles.detailContainer}>
        <Text style={styles.label}>Adresse</Text>
        <View style={styles.detailRow}>
          <Text style={styles.value}>{client.address}</Text>
          <Icon name="map-marker" size={20} color="#007BFF" />
        </View>
        <Text style={styles.value}>{client.postalCode} {client.city}</Text>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
    backgroundColor: '#fff',
  },
  header: {
    alignItems: 'center',
    marginBottom: 20,
  },
  avatar: {
    width: 80,
    height: 80,
    borderRadius: 40,
    backgroundColor: '#ddd',
    justifyContent: 'center',
    alignItems: 'center',
    marginBottom: 10,
  },
  avatarText: {
    fontSize: 40,
    color: '#fff',
  },
  name: {
    fontSize: 24,
    fontWeight: 'bold',
  },
  detailContainer: {
    marginBottom: 15,
  },
  label: {
    fontWeight: 'bold',
  },
  detailRow: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
  },
  value: {
    fontSize: 16,
  },
  editButton: {
    color: '#007BFF',
    fontWeight: 'bold',
    marginRight: 15,
    fontSize: 16,
  },
});

export default ClientProfileScreen;
