<template>
  <div>
    <NavBar></NavBar>
    <div class="mt-4">
      <v-app>
        <v-form
            ref="form"
            v-model="valid"
            class="p-5"
            @submit.prevent="addMedicine"
        >
          <v-text-field
              v-model="medicine.name"
              :rules="nameRules"
              label="Name"
              required
          ></v-text-field>

          <v-text-field
              v-model="medicine.avgPrice"
              label="Avg Price"
              type="number"
              step="0.1"
              min="0.0"
              :rules="avgPriceRules"
              required
          ></v-text-field>

          <v-autocomplete
              chips
              clearable
              deletable-chips
              multiple
              small-chips
              :items="ingredients"
              v-model="medicine.ingredients"
              item-text="name"
              item-value="id"
              label="Ingredients"
          ></v-autocomplete>

          <v-autocomplete
              chips
              clearable
              deletable-chips
              multiple
              small-chips
              :items="goodForDiseases"
              :item-text="item => item.name + ' '+ item.surname + ' ' + item.pesel"
              item-value="id"
              v-model="medicine.goodForDiseases"
              label="Good for"
          ></v-autocomplete>

          <v-btn
              :disabled="!valid || loading"
              class="mr-4"
              :loading="loading"
              type="submit"
              v-show="!exists"
          >
            Create
          </v-btn>
          <v-btn
              :disabled="!valid || loading"
              class="mr-4"
              @click="updateMedicine"
              :loading="loading"
              v-show="exists"
          >
            Update
          </v-btn>
          <v-btn
              :disabled="!valid || loading"
              class="mr-4"
              @click="deleteMedicine"
              :loading="loading"
              v-show="exists"
          >
            Delete
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
      avgPriceRules: [v => !!v || 'Treatment length is required'],
      ingredients: ["abacavir", "adenosine", "ampicillin sodium", "eculizumab", "elotuzumab", "famotidine", "felodipine", "ibuprofen", "magnesium", "metyrapone", "ramipril", "valsartan"],
      goodForDiseases: ["Flu", "Conjunctivitis", "Diarrhea","Headache", "Mononucleosis", "Stomach Aches"],
      chosenDiseases: [],
      medicine: {},
      snackbar: false,
      snackbarText: "",
      loading: false,
      exists: false,
    }
  },
  methods: {
    addMedicine() {
      this.loading = true;
      ApiServices.addMedicine(this.medicine).then(() => {
        this.snackbarText = "Medicine saved successfully";
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
    },
    deleteMedicine(){
      ApiServices.deleteMedicine(this.$route.params.id).then(() => {
        this.$router.push({name: 'medicines'});
      });
    },
    updateMedicine(){
      ApiServices.updateMedicine(this.medicine).then(() => {
        this.snackbarText = "Medicine updated successfully";
        this.snackbar = true;
      });
    }
  },
  created() {
    if (this.$route.params.id && this.$route.params.id !== "add") {
      this.getMedicine();
      this.exists = true;
    }
  },
  computed: {
    isMedicineExists() {
      return this.$route.params.id !== 'add'
    },
  }
}
</script>

<style scoped>

</style>
