<template>
  <div>
    <h1>Event registration</h1>
    <router-link :to="{name:'Hello'}">Hello Page</router-link>
    <!--
      Alternatively, specify the page URL:
      <router-link to="/hello">Hello Page</router-link>

      Or navigate with JavaScript (use this.$router instead of $router if you put this in your script section):
      <button @click="$router.push({name: 'Hello'})">Hello Page</button>

      Or navigate with JavaScript and do NOT let the user go back:
      <button @click="$router.replace({name: 'Hello'})">Hello Page</button>

      Or use a raw <a> tag (not recommended):
      <a href="/#/hello">Hello Page</a>
    -->
    <p>Welcome to the event registration app!</p>
    <h2>Users</h2>
    <table>
      <tr>
        <th>Name</th>
      </tr>
      <tr v-for="u in users" :key="u.id">
        <td>{{u.name}}</td>
      </tr>
      <tr>
        <td><input type="text" placeholder="Name" v-model="newUserName"></td>
        <td><b-button variant="primary" v-bind:disabled="!newUserName.trim()" @click="createUser(newUserName)">Create</b-button></td>
      </tr>
    </table>
    <h2>Events</h2>
    <table>
      <tr>
        <th>Name</th>
        <th>Date</th>
        <th>Start Time</th>
        <th>End Time</th>
      </tr>
      <tr v-for="e in events" :key="e.name">
        <td>{{e.name}}</td>
        <td>{{e.eventDate}}</td>
        <td>{{e.startTime}}</td>
        <td>{{e.endTime}}</td>
      </tr>
      <tr>
        <td><input type="text" placeholder="Name" v-model="newEventName"></td>
        <td><input type="text" placeholder="Date" v-model="newEventDate"></td>
        <td><input type="text" placeholder="Start time" v-model="newEventStartTime"></td>
        <td><input type="text" placeholder="End time" v-model="newEventEndTime"></td>
        <td><b-button v-bind:disabled="eventBtnDisabled" @click="createEvent(newEventName, newEventDate, newEventStartTime, newEventEndTime)">Create</b-button></td>
      </tr>
    </table>
  </div>
</template>

<script>
import axios from 'axios'
const config = require('../../config')

const frontendUrl = config.dev.host + ':' + config.dev.port
const axiosClient = axios.create({
  // Note the baseURL, not baseUrl
  baseURL: config.dev.backendBaseUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
  name: 'Home',
  data() {
    return {
      users: [],
      newUserName: '',
      events: [],
      newEventName: '',
      newEventDate: '',
      newEventStartTime: '',
      newEventEndTime: '',
      nextId: 1
    }
  },
  created() {
    axiosClient.get('/person')
    .then(response => {
      console.log(response)
      this.users = response.data.persons
    })
    .catch(e => {
      console.log('Error in GET /person:')
      console.log(e)
    })

    axiosClient.get('/event')
    .then(response => {
      console.log(response)
      this.events = response.data.events
      this.nextId = response.data.events.length
    })
    .catch(e => {
      console.log('Error in GET /event:')
      console.log(e)
    })
  },
  methods: {
    createUser: function(userName) {
      axiosClient.post('/person', {
        name: userName
      })
      .then(response => {
        console.log(response)
        this.users.push(response.data)
        this.newUserName = ''
      })
      .catch(error => {
        console.log(error)
      })
    },
    createEvent: function(eventName, date, startTime, endTime) {
      axiosClient.post('/event', {
        name: eventName,
        eventDate: date,
        startTime: startTime,
        endTime: endTime
      })
      .then(response => {
        console.log(response)
        this.events.push(response.data)
        this.newEventName = ''
        this.newEventDate = ''
        this.newEventStartTime = ''
        this.newEventEndTime = ''
      })
      .catch(error => {
        console.log(error)
      })      
    }
  },
  computed: {
    eventBtnDisabled() {
      return !this.newEventName.trim() || !this.newEventDate.trim() || !this.newEventStartTime.trim() || !this.newEventEndTime.trim() 
    }
  }
}
</script>

<style>
.btn-primary:not(disabled) {
  background-color: green;
  color: black;
  border: 5px solid darkgreen;
}
.btn-primary:hover {
  background-color: red;
  color: black;
  border: 10px dashed darkred;
}
</style>
