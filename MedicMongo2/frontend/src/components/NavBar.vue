<template>
  <v-card class="overflow-hidden">
    <v-app-bar
        absolute
        color="white"
        elevate-on-scroll
        scroll-target="#scrolling-techniques-7"
    >
      <v-toolbar-title>MedicMongo</v-toolbar-title>

      <v-spacer></v-spacer>
      <div v-if="ifPatient">
        <v-btn class="m-2" @click="routeToPatientProfile">
          <span>Profile</span>
          <v-icon>mdi-account</v-icon>
        </v-btn>

        <v-btn class="m-2" @click="routeToResults">
          <span>Results&nbsp;</span>
          <v-icon>mdi-calendar-text</v-icon>
        </v-btn>


        <v-btn class="m-2" @click="logout">
          <span>Logout&nbsp;</span>
          <v-icon>mdi-logout</v-icon>
        </v-btn>
      </div>

      <div v-if="ifDoctor">
        <v-btn class="m-2" @click="routeToMedicines">
          <span>Medicines&nbsp;</span>
          <v-icon>mdi-pill</v-icon>
        </v-btn>

        <v-btn class="m-2" @click="routeToAddMedicine">
          <span>Add Medicine&nbsp;</span>
          <v-icon>mdi-medical-bag</v-icon>
        </v-btn>

        <v-btn class="m-2" @click="routeToAddResult">
          <span>Add results&nbsp;</span>
          <v-icon>mdi-calendar-plus</v-icon>
        </v-btn>

        <v-btn class="m-2" @click="routeToAddPatient">
          <span>Add Patient&nbsp;</span>
          <v-icon>mdi-account-multiple-plus</v-icon>
        </v-btn>

        <v-btn class="m-2" @click="routeToDoctorProfile">
          <span>Profile&nbsp;</span>
          <v-icon>mdi-account</v-icon>
        </v-btn>

        <v-btn class="m-2" @click="logout">
          <span>Logout&nbsp;</span>
          <v-icon>mdi-logout</v-icon>
        </v-btn>
      </div>

      <v-spacer></v-spacer>
    </v-app-bar>
    <v-sheet
        id="scrolling-techniques-7"
        class="overflow-y-auto"
        max-height="60"
    >
      <v-container style="height: 1500px;">
      </v-container>
    </v-sheet>
  </v-card>
</template>
<script>
export default {
  methods: {
    logout() {
      localStorage.clear();
      this.$router.push({name: 'login'});
    },
    routeToResults() {
      this.$router.push({name: 'results'});
    },
    routeToPatientProfile() {
      this.$router.push({name: 'patient', params: {id: localStorage.getItem('id')}});
    },
    routeToDoctorProfile() {
      this.$router.push({name: 'doctor', params: {id: localStorage.getItem('id')}});
    },
    routeToAddResult() {
      this.$router.push({name: 'result-add'});
    },
    routeToMedicines() {
      this.$router.push({name: 'medicines'});
    },
    routeToAddMedicine() {
      this.$router.push({name: 'medicine-add'});
    },
    routeToAddPatient() {
      this.$router.push({name: 'patient-add'});
    },
  },

  computed: {
    ifLoggedUser() {
      return !localStorage.getItem('login');
    },

    ifDoctor() {
      return localStorage.getItem('role') === "DOCTOR"
    },

    ifPatient() {
      return localStorage.getItem('role') === "PATIENT"
    }
  }
}
</script>

<style scoped>

</style>
