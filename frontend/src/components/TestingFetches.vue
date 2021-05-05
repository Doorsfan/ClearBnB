<template>
  <div>
  <div>
    FETCH TESTING FOR BOOKINGS
    <button @click="tryGETRequestForBookings" class="testGetRequest" value="Test Get Request">Test Get Request to Bookings</button>
    <button @click="tryGETRequestForBookingsWithId" value="Test Get Request to Bookings With Id">Test Get Request to Bookings with Id</button>
    <button @click="tryPOSTRequestForBookings" value="Test Post Request to Bookings" class="testPostRequest">Test Post Request to Bookings</button>
    <button @click="tryDELETERequestForBookingsWithId" class="testDeleteRequest" value="Test Delete Request to Bookings with Id">Test Delete Request to Bookings with Id</button>
  </div>
  <div>
    FETCH TESTING FOR LEASES
    <button @click="tryPOSTRequestForLeases" value="Test Post Request to Leases">Test Post Request to Leases</button>
    <button @click="tryGETRequestForLeasesWithoutId" value="Test Get Request to Leases Without Id">Test Get Request to Leases Without Id</button>
    <button @click="tryGETRequestForLeasesWithId" value="Test Get Request to Leases With Id">Test Get Request to
    Leases With Id</button>
    <button @click="tryDELETERequestForLeasesBasedOnId" value="Test Delete Request to Leases Based on Id">Test Delete Request to Leases Based on Id</button>
  </div>
  <div>
    FETCH TESTING FOR USERINFO
    <button @click="tryPOSTRequestForUserinfos" value="Test Post Request for User Infos">Test Post Request to Userinfos</button>
    <button @click="tryGETRequestForUserinfos" value="Test Get Request for User Infos">Test Get Request to Userinfos</button>
    <button @click="tryGETRequestForUserInfosWithId" value="Test Get Request for User Info with Id">Test Get Request to Userinfos With Id</button>
    <button @click="tryDELETERequestForUserInfosWithId" value="Test Delete Request for User Info with Id">Test Delete Request to Userinfos With Id</button>
  </div>
  <div>
    FETCH TESTING FOR API PATHS
    <button @click="tryPOSTRequestForApiRegister" value="Test Post Request for Api Register">Test Post Request to Api Register</button>
    <button @click="tryPOSTRequestForApiLogin" value="Test Post Request for Api Login">Test Post Request to Api Login</button>
    <button @click="tryGETRequestForApiWhoAmI" value="Test Get Request for Api WhoAmI">Test Get Request for Api WhoAmI</button>
    <button @click="tryGETRequestForApiLogout" value="Test Get Request for Logout">Test Get Request for Logout</button>
  </div>
  </div>
</template>

<script>
export default {
  data(){
    return {

    }
  },
  methods: {
    async tryGETRequestForBookings(){
      let res = await fetch('/rest/bookings');
      console.log(await res.json());
    },
    async tryGETRequestForBookingsWithId(){
      let res = await fetch('/rest/bookings/:id')
      console.log(await res.json());
    },
    async tryPOSTRequestForBookings(){
      let booking = {
        userId: 1,
        leaseId: 1,
        location: "Los Angeles",
        startDate: "2021-04-05",
        endDate: "2021-05-05",
        guests: 1,
        totalPrice: 1000
      }
      let res = await fetch('/rest/bookings', {
        method: 'POST',
        body: JSON.stringify(booking)
      })
      console.log(await res.json());
    },
    async tryDELETERequestForBookingsWithId(){
      let res = await fetch('/rest/bookings/u8wIeBkOCW-K_upXkmLhb',
      {
        method: 'DELETE'
      })
      console.log(await res.json());
    },
    async tryPOSTRequestForLeases(){
      let amenities = {
        wifi: true,
        kitchen: false,
        washer: false,
        heating: false,
        airConditioner: false
      }
      let amenitiesAsJson = JSON.stringify(amenities);
      let lease = {
        ownerId: 1,
        title: "Cottage in LA",
        location: "Los Angeles",
        description: "Lorem Ipsum",
        typeOfHousing: "House",
        startDate: "2021-04-05",
        endDate: "2021-05-05",
        pricePerNight: 1000,
        maxGuests: 1,
        beds: 1,
        amenities: amenitiesAsJson,
        imageURLs: []
      }
      let res = await fetch('/rest/leases', {
        method: 'POST',
        body: JSON.stringify(lease)
      })
      console.log(await res.json());
    },
    async tryGETRequestForLeasesWithoutId(){
      let res = await fetch('/rest/leases')
      console.log(await res.json());
    },
    async tryGETRequestForLeasesWithId(){
      let res = await fetch('/rest/leases/:id')
      console.log(await res.json());
    },
    async tryDELETERequestForLeasesBasedOnId(){
      let res = await fetch('/rest/leases/:id', {
        method: 'DELETE'
      })
      console.log(await res.json());
    },
    async tryPOSTRequestForUserinfos(){
      let userinfos = {
        userId: 1,
        firstName: "John",
        lastName: "Doe",
        streetAddress: "Example Street 2",
        zipCode: "666 666",
        city: "Jönnköping",
        country: "Sweden",
        phoneNumber: "046705 - 102135"
      }
      let res = await fetch('/rest/userinfos', {
        method: 'POST',
        body: JSON.stringify(userinfos)
      })
      console.log(await res.json());
    },
    async tryGETRequestForUserinfos(){
      let res = await fetch('/rest/userinfos')
      console.log(await res.json());
    },
    async tryGETRequestForUserInfosWithId(){
      let res = await fetch('/rest/userinfos/:id')
      console.log(await res.json());
    },
    async tryDELETERequestForUserInfosWithId(){
      let res = await fetch('/rest/userinfos/:id', {
        method: 'DELETE'
      })
      console.log(await res.json());
    },
    async tryPOSTRequestForApiRegister(){
      let user = {
        username: "john.doe@hotmail.com",
        password: "supersecret"
      }
      let res = await fetch('/api/register', {
        method: 'POST',
        body: JSON.stringify(user)
      })
      console.log(await res.json());
    },
    async tryPOSTRequestForApiLogin(){
      let user = {
        username: "john.doe@hotmail.com",
        password: "supersecret"
      }
      let res = await fetch('/api/login', {
        method: 'POST',
        body: JSON.stringify(user)
      })
      console.log(await res.json());
    },
    async tryGETRequestForApiWhoAmI(){
      let res = await fetch('/api/whoami')
      console.log(await res.json());
    },
    async tryGETRequestForApiLogout(){
      let res = await fetch('/api/logout');
      console.log(await res.json());
    }
  }
}
</script>

<style scoped>
a {
  color: #42b983;
}
button {
  margin: 5px;
}
</style>
