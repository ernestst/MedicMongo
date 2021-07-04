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
              <b>{{patient.name}} {{patient.surname}}</b>
            </div>

            <v-list-item-title class="text-h5 mb-1 mt-3">
              {{ patient.pesel }}
            </v-list-item-title>
            <v-list-item-subtitle>Pesel</v-list-item-subtitle>

            <v-list-item-title class="text-h5 mb-1 mt-3">
              {{ patient.sex }}
            </v-list-item-title>
            <v-list-item-subtitle>Sex</v-list-item-subtitle>

            <v-list-item-title class="text-h5 mb-1 mt-3">
              {{ patient.email }}
            </v-list-item-title>
            <v-list-item-subtitle>E-mail</v-list-item-subtitle>

            <v-spacer></v-spacer>

            <v-list-item-title class="text-h5 mt-3 mb-0" v-if="patient.allergies">
              Allergies
            </v-list-item-title>

            <v-list-item v-if="patient.allergies">
              <v-list-item-content>
                <v-list-item-subtitle class="text-h5" v-for="allergy in patient.allergies" :key="allergy">
                  {{ allergy }}
                </v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>

            <v-list-item-title class="text-h5 mt-3 mb-0" v-if="patient.chronicDiseases">
              Chronic Diseases
            </v-list-item-title>

            <v-list-item v-if="patient.chronicDiseases">
              <v-list-item-content>
                <v-list-item-subtitle class="text-h5" v-for="chronicDisease in patient.chronicDiseases" :key="chronicDisease">
                  {{ chronicDisease }}
                </v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>

          </v-list-item-content>

          <v-list-item-avatar
              tile
              size="80"
              color="grey"
          >
            <v-img :src="(patient.sex && patient.sex.toLowerCase() === 'male') ? images.patient_male : images.patient_female"></v-img>
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
  data() {
    return {
      patient: {},
      images: {
        patient_male: require('@/assets/male.png'),
        patient_female: require('@/assets/female.png')
      },
      loading: true
    }
  },
  methods: {
    getPatient() {
      ApiServices.findPatientById(this.$route.params.id).then(response => {
        this.patient = response.data;
        this.loading = false;
      });
    },
  },

  created() {
    this.getPatient();
  }
}
</script>

<style scoped>

</style>
