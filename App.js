import "react-native-gesture-handler";
import * as React from "react";
import { NavigationContainer } from "@react-navigation/native";
import { createStackNavigator } from "@react-navigation/stack";
import HomeScreen from "./src/screens/HomeScreen.js";
import ClientListScreen from "./src/screens/ClientListScreen.js";
import ClientDetailScreen from "./src/screens/ClientDetailScreen.js";
import ClientProfileScreen from "./src/screens/ClientProfileScreen.js";

const Stack = createStackNavigator();

function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Home">
        <Stack.Screen name="Home" component={HomeScreen} />
        <Stack.Screen
          name="ClientList"
          component={ClientListScreen}
          options={{ title: "Annuaire" }}
        />
        <Stack.Screen
          name="ClientDetail"
          component={ClientDetailScreen}
          options={{ title: "Edition" }}
        />
        <Stack.Screen
          name="ClientProfile"
          component={ClientProfileScreen}
          options={{ title: "Edition" }}
        />
      </Stack.Navigator>
    </NavigationContainer>
  );
}

export default App;
