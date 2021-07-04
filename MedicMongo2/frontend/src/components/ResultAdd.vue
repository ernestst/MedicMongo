<template>
  <div>
    <NavBar></NavBar>
    <div class="mt-4">
      <v-app>
        <v-form
            ref="form"
            v-model="valid"
            class="p-5"
        >
          <v-text-field
              v-model="disease"
              :rules="diseaseRules"
              label="Disease"
              required
          ></v-text-field>

          <v-text-field
              v-model="treatmentLength"
              :rules="treatmentLengthRules"
              label="Treatment Length"
              type="number"
              step="1"
              min="0.0"
              required
          ></v-text-field>

          <v-checkbox
              v-model="shouldStayHome"
              label="Should patient stay home?"
              required
          ></v-checkbox>

          <v-autocomplete
              chips
              clearable
              deletable-chips
              multiple
              small-chips
              hide-no-data
              return-object
              hide-selected
              v-model="medicinesRequired"
              item-text="name"
              item-value="id"
              label="Medicines"
              :items="medicines"
              :loading="medicineLoading"
              :search-input.sync="medicineSearch"
          ></v-autocomplete>

          <v-autocomplete
              return-object
              v-model="chosenPatient"
              item-text="pesel"
              item-value="pesel"
              label="Patient"
              :items="patients"
              :loading="patientLoading"
              :search-input.sync="patientSearch"
          ></v-autocomplete>

          <v-menu
              ref="menu"
              v-model="menu"
              :close-on-content-click="false"
              :return-value.sync="date"
              transition="scale-transition"
              offset-y
              min-width="auto"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-text-field
                  v-model="date"
                  label="Examination date"
                  readonly
                  v-bind="attrs"
                  v-on="on"
                  required
                  :rules="examinationDateRules"
              ></v-text-field>
            </template>
            <v-date-picker
                v-model="date"
                no-title
                scrollable
            >
              <v-spacer></v-spacer>
              <v-btn
                  text
                  color="primary"
                  @click="menu = false"
              >
                Cancel
              </v-btn>
              <v-btn
                  text
                  color="primary"
                  @click="$refs.menu.save(date)"
              >
                OK
              </v-btn>
            </v-date-picker>
          </v-menu>

          <v-btn
              :disabled="!valid || loading"
              class="mr-4 mt-3"
              @click="create"
              :loading="loading"
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
      menu: false,
      valid: false,
      disease: "",
      diseaseRules: [v => !!v || 'Disease is required'],
      treatmentLength: "",
      treatmentLengthRules: [v => !!v || 'Treatment length is required'],
      examinationDateRules: [v => !!v || 'Examination date is required'],
      shouldStayHome: false,
      medicines: [],
      medicinesRequired: [],
      patients: [],
      chosenPatient: {},
      date: null,
      loading: false,
      snackbar: false,
      snackbarText: '',
      doctorId: '',
      medicineLoading: false,
      medicineSearch: null,
      patientLoading: false,
      patientSearch: null
    }
  },
  methods: {
    getDoctorId() {
      ApiServices.getLoggedDoctor({
        login: localStorage.getItem('login'),
        password: localStorage.getItem('password')
      }).then(response => {
        this.doctorId = response.data;
      });
    },
    create() {
      this.loading = true;
      const medicinesRaw = [];
      for (let medicine of this.medicinesRequired) {
          medicinesRaw.push(medicine.id);
      }

      ApiServices.addResult({
        examinationDate: this.date,
        patientId: this.chosenPatient.id,
        doctorId: this.doctorId,
        disease: this.disease,
        stayHome: this.shouldStayHome,
        treatmentLength: this.treatmentLength,
        medicines: medicinesRaw
      }).then(() => {
        this.snackbarText = "Result saved successfully";
        this.snackbar = true;
        this.loading = false;
      }, (error) => {
        this.snackbarText = error;
        this.snackbar = true;
        this.loading = false;
      })
    }
  },
  created() {
    this.getDoctorId();
  },
  watch: {
    medicineSearch (val) {
      if (this.medicineLoading) return
      if (this.medicinesRequired.length !== 0) this.medicines.reduce(medicine => this.medicinesRequired.includes(medicine));

      this.medicineLoading = true
      ApiServices.findMedicines(0, val).then(response => {
        response.data.content.forEach(item => this.medicines.push(item))
      }).catch(err => {
        console.log(err)
      }).finally(() => (this.medicineLoading = false))
    },
    patientSearch (val) {
      if (this.patientLoading) return

      this.patientLoading = true
      ApiServices.findPatientByPesel(0, val).then(response => {
        this.patients = response.data.content;
      }).catch(err => {
        console.log(err)
      }).finally(() => (this.patientLoading = false))
    },
  }
}
</script>

<style scoped>

</style>
