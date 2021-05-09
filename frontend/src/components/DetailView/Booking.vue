<template>
</template>
<script>
import Lease from '../../components/Lease.vue'
export default {  
  name: 'Booking',
  components: 
  {
    Lease
  },
  data(){
    return {
      location: '',
      startDate: '',
      endDate: '',
      guests: '',
      totalPrice: '',
      bookedStay: ''
    }
  }
}
</script>
