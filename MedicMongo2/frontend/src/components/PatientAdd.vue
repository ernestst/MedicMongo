<template>
  <div>
    <NavBar></NavBar>
    <div class="mt-4">
      <v-app>
        <v-form
            ref="form"
            v-model="valid"
            class="p-5"
            @submit.prevent="addPatient"
        >
          <v-text-field
              v-model="patient.name"
              :rules="nameRules"
              label="Name"
              required
          ></v-text-field>

          <v-text-field
              v-model="patient.surname"
              :rules="surnameRules"
              label="Surname"
              required
          ></v-text-field>

          <v-text-field
              v-model="patient.pesel"
              :rules="peselRules"
              label="Pesel"
              required
          ></v-text-field>

          <v-text-field
              v-model="patient.email"
              :rules="emailRules"
              label="Email"
              required
          ></v-text-field>

          <v-text-field
              v-model="patient.sex"
              label="Sex"
          ></v-text-field>

          <template v-slot:activator="{ on, attrs }">
            <v-text-field
                v-model="patient.birthdate"
                label="Examination date"
                readonly
                v-bind="attrs"
                v-on="on"
                required
                :rules="birthdateRules"
            ></v-text-field>
          </template>

          <v-autocomplete
              chips
              clearable
              deletable-chips
              multiple
              small-chips
              :items="allergies"
              v-model="patient.allergies"
              item-text="name"
              item-value="id"
              label="Allergies"
          ></v-autocomplete>

          <v-autocomplete
              chips
              clearable
              deletable-chips
              multiple
              small-chips
              :items="chronicDiseases"
              v-model="patient.chronicDiseases"
              item-text="name"
              item-value="id"
              label="Chronic Diseases"
          ></v-autocomplete>

          <v-btn
              :disabled="!valid || loading"
              class="mr-4"
              :loading="loading"
              type="submit"
          >
            Create
          </v-btn>
        </v-form>

        <v-snackbar
            v-model="snackbar"
        >
          {{ snackbarText }}

          <template v-slot:action="{ attrs }">
            <v-btn
                color="pink"
                text
                v-bind="attrs"
                @click="snackbar = false"
            >
              Close
            </v-btn>
          </template>
        </v-snackbar>
      </v-app>
    </div>
  </div>
</template>

<script>
import NavBar from "./NavBar";
import ApiServices from '../api-services';

export default {
  components: {NavBar},
  data() {
    return {
      valid: false,
      nameRules: [v => !!v || 'Name is required'],
      surnameRules: [v => !!v || 'Surname is required'],
      peselRules: [v => !!v || 'Pesel is required'],
      emailRules: [v => !!v || 'Email is required'],
      birthdateRules: [v => !!v || 'Birthdate is required'],
      allergies: ["Ibuprofen", "Aspirin", "Nuts", "Shellfish", "Eggs", "Milk", "Latex", "Mould", "Dust Mites", "Hair Dyes"],
      chronicDiseases: ["Alzheimer", "Arthritis", "Asthma", "Cancer", "Diabetes", "Heart diseas ", "High blood pressure", "High cholesterol", "Lower back pain", "Migraine headaches", "Stroke"],
      patient: {},
      snackbar: false,
      snackbarText: "",
      loading: false
    }
  },
  methods: {
    addPatient() {
      this.loading = true;
      ApiServices.addPatient(this.patient).then(() => {
        this.snackbarText = "Patient saved successfully";
        this.snackbar = true;
        this.loading = false;
      }, (error) => {
        this.snackbarText = error;
        this.snackbar = true;
        this.loading = false;
      })
    },
    getMedicine() {
      ApiServices.getMedicine(this.$route.params.id).then(response => {
        this.medicine = response.data;
      });
    }
  },
  created() {
    if (this.$route.params.id) {
      this.getMedicine();
    }
  }
}
</script>

<style scoped>

</style>
