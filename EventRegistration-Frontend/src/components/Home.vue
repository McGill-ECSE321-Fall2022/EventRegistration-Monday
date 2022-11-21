<template>
  <div>
    <h1>Event registration</h1>
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
        <td><button v-bind:disabled="!newUserName.trim()" @click="createUser(newUserName)">Create</button></td>
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
        <td>{{e.date}}</td>
        <td>{{e.startTime}}</td>
        <td>{{e.endTime}}</td>
      </tr>
      <tr>
        <td><input type="text" placeholder="Name" v-model="newEventName"></td>
        <td><input type="text" placeholder="Date" v-model="newEventDate"></td>
        <td><input type="text" placeholder="Start time" v-model="newEventStartTime"></td>
        <td><input type="text" placeholder="End time" v-model="newEventEndTime"></td>
        <td><button v-bind:disabled="eventBtnDisabled" @click="createEvent(newEventName, newEventDate, newEventStartTime, newEventEndTime)">Create</button></td>
      </tr>
    </table>
  </div>
</template>

<script>
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
    this.users.push({ id: 1, name: 'Alice' })
    this.users.push({ id: 2, name: 'Bob' })
    this.nextId = 3
  },
  methods: {
    createUser: function(userName) {
      const u = { id: this.nextId, name: userName }
      this.users.push(u)
      this.nextId++
      this.newUserName = ''
    },
    createEvent: function(eventName, date, startTime, endTime) {
      const e = { name: eventName, date: date, startTime: startTime, endTime: endTime }
      this.events.push(e)
      this.newEventName = ''
      this.newEventDate = ''
      this.newEventStartTime = ''
      this.newEventEndTime = ''
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

</style>
