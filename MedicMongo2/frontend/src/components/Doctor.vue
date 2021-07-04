<template>
  <div>
    <NavBar></NavBar>
    <div class="mt-4">
      <v-card
          class="mx-auto p-3"
          max-width="50%"
          outlined
      >
        <v-list-item three-line v-if="!loading">
          <v-list-item-content>
            <div class="text-overline mb-4">
              <b>Doctor {{doctor.name}} {{doctor.surname}}</b>
            </div>
            <v-list-item-title class="text-h5 mb-1">
              {{ doctor.speciality }}
            </v-list-item-title>
            <v-list-item-subtitle>Speciality</v-list-item-subtitle>

            <v-list-item-title class="text-h5 mb-1 mt-3">
              {{ doctor.college }}
            </v-list-item-title>
            <v-list-item-subtitle>College</v-list-item-subtitle>

            <v-list-item-title class="text-h5 mb-1 mt-3">
              {{ doctor.licenseNumber }}
            </v-list-item-title>
            <v-list-item-subtitle>License</v-list-item-subtitle>

            <v-list-item-title class="text-h5 mb-1 mt-3">
              {{ doctor.email }}
            </v-list-item-title>
            <v-list-item-subtitle>E-mail</v-list-item-subtitle>

            <v-list-item-title class="text-h5 mb-1 mt-3">
              {{ doctor.sex }}
            </v-list-item-title>
            <v-list-item-subtitle>Sex</v-list-item-subtitle>
          </v-list-item-content>

          <v-list-item-avatar
              tile
              size="80"
              color="grey"
          >
            <v-img :src="images.doctor"></v-img>
          </v-list-item-avatar>
        </v-list-item>

        <v-layout align-center justify-center column fill-height>
          <v-flex row align-center>
            <v-progress-circular
                :size="150"
                class="mx-auto mt-3 mb-3"
                max-width="50%"
                indeterminate
                v-if="loading"
            ></v-progress-circular>
          </v-flex>
        </v-layout>
      </v-card>
    </div>
  </div>
</template>

<script>
import NavBar from "./NavBar";
import ApiServices from '../api-services';
export default {
  components: {NavBar},
  name: "Doctor",
  data() {
    return {
      doctor: {},
      images: {
        doctor: require('@/assets/doctor.png')
      },
      loading: true
    }
  },
  methods: {
    getDoctor() {
      ApiServices.findDoctorById(this.$route.params.id).then(response => {
        this.doctor = response.data;
        this.loading = false;
      });
    },
  },

  created() {
    this.getDoctor();
  }
}
</script>

<style scoped>

</style>
