<template>
  <v-app>
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
              <b>Results from {{result.examinationDate}}</b>
            </div>
            <v-list-item-title class="text-h5 mb-1">
              {{ result.disease }}
            </v-list-item-title>
            <v-list-item-subtitle>Disease</v-list-item-subtitle>

            <v-list-item-title class="text-h5 mb-1 mt-3">
              {{ result.treatmentLength }}
            </v-list-item-title>
            <v-list-item-subtitle>Treatment Length</v-list-item-subtitle>

            <v-list-item-title class="text-h5 mb-1 mt-3">
              {{ result.stayHome }}
            </v-list-item-title>
            <v-list-item-subtitle>Should stay home</v-list-item-subtitle>

            <div v-for="medicine in result.medicinesObjects" v-bind:key="medicine.name" class="mt-1" style="display: flex">
              <v-list-item-title class="text-h5">
                {{ medicine.name }}
              </v-list-item-title>
            </div>
            <v-list-item-subtitle class="mt-2">Medicines</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>

        <div class="text-center">
          <v-dialog
              v-model="dialog"
              width="200"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                  color="red lighten-2"
                  dark
                  v-bind="attrs"
                  v-on="on"
              >
                Delete
              </v-btn>
            </template>

            <v-card>
              <v-card-title class="text-h5 grey lighten-2">
                Are you sure?
              </v-card-title>

              <v-card-actions>
                <v-btn
                    color="primary"
                    text
                    @click="deleteResult"
                >
                  Yes
                </v-btn>
                <v-btn
                    color="primary"
                    text
                    @click="dialog = false"
                >
                  No
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </div>

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
  </v-app>
</template>

<script>
import NavBar from "./NavBar";
import ApiServices from '../api-services';
export default {
  components: {NavBar},
  data() {
    return {
      result: {},
      dialog: "",
      loading: true
    }
  },
  methods: {
    getResult() {
      ApiServices.findResultById(this.$route.params.id).then(response => {
        this.result = response.data;
        this.getMedicines()
      });
    },
    deleteResult(){
      this.dialog = false
      ApiServices.deleteResult(this.$route.params.id).then(() => {
        this.$router.push({name: 'results'});
      });
    },
    getMedicines(){
      this.result.medicinesObjects = []
      let count = 0;
      for(const i in this.result.medicines){
        ApiServices.getMedicine( this.result.medicines[i]).then(response => {
          this.result.medicinesObjects.push(response.data)
          count++;
          if(this.result.medicines.length == count){
            this.loading = false;
          }
        });
      }
    }
  },

  created() {
    this.getResult();
  }
}
</script>

<style scoped>

</style>
