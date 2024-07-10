import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/api',
  headers: {
    'Accept': 'application/api.rest-v1+json',
    'Content-Type': 'application/json'
  }
});

export const authenticate = async (username, password) => {
  try {
    const response = await api.post('/auth', {
      username,
      password,
      password_type: 0,
      code_application: 'webservice_externe',
      code_version: '1'
    });
    return response.data.datas.token;
  } catch (error) {
    console.error('Authentication error:', error);
    return null;
  }
};

export const getClients = async (token) => {
  try {
    const response = await api.get('/clients', {
      headers: {
        Authorization: `Bearer ${token}`
      }
    });
    return response.data.datas;
  } catch (error) {
    console.error('Get clients error:', error);
    return null;
  }
};