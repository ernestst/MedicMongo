<template>
  <div>
    <NavBar></NavBar>
    <v-card-title>
      Medicine name
      <v-spacer></v-spacer>
      <v-form
          ref="form"
          class="mx-auto w-50"
      >
        <v-text-field
            v-model="search"
            append-icon="mdi-magnify"
            label="Search"
            single-line
            hide-details
        ></v-text-field>
      </v-form>
    </v-card-title>

    <v-data-table
        :headers="headers"
        :items="medicines"
        item-key="id"
        :search="search"
        :server-items-length="itemsLength"
        :items-per-page="perPage"
        @update:page="getMedicines"
        @click:row="routeToMedicine"
        class="mx-auto p-4 row-pointer"
        loading
        loading-text="Loading..."
    >
    </v-data-table>
  </div>
</template>

<script>
import NavBar from "./NavBar";
import ApiServices from '../api-services';
import lodash from 'lodash'

export default {
  components: {NavBar},
  name: "Results",
  data() {
    return {
      headers: [{text: 'Name', value: 'name'}, {text: 'Avg Price', value: 'avgPrice'}],
      medicines: [],
      search: '',
      perPage: 10,
      itemsLength: 0
    }
  },
  methods: {
    getMedicines(pagination) {
      if (this.search === '' || this.search === undefined) {
        ApiServices.getAllMedicines((pagination) ? pagination - 1 : 0).then(response => {
          this.medicines = response.data.content;
          this.perPage = response.data.pageable.size;
          this.itemsLength = response.data.totalElements
        });
      } else {
        ApiServices.findMedicines((pagination) ? pagination - 1 : 0, this.search).then(response => {
          this.medicines = response.data.content;
          this.perPage = response.data.pageable.size;
          this.itemsLength = response.data.totalElements
        });
      }
    },
    routeToMedicine(medicine) {
      this.$router.push({
        name: 'medicine', params: {id: medicine.id}
      });
    }
  },
  created() {
    this.getMedicines(1);
    this.debouncedGetAnswer = lodash.debounce(this.getMedicines, 500)
  },
  watch: {
    search: function () {
      this.debouncedGetAnswer()
    }
  }
}
</script>

<style scoped>
.v-data-footer__select {
  visibility: hidden
}
.row-pointer >>> tbody tr :hover {
  cursor: pointer;
}
</style>
