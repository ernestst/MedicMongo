<template>
  <div>
    <NavBar></NavBar>
    <v-card-title>
      Disease Name
      <v-spacer></v-spacer>
      <v-text-field
          v-model="search"
          label="Search"
          append-icon="mdi-magnify"
          single-line
          hide-details
      ></v-text-field>
    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="results"
        item-key="id"
        :search="search"
        :server-items-length="itemsLength"
        :items-per-page="perPage"
        @update:page="getResults"
        @click:row="routeToResult"
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
      headers: [{text: 'Disease', value: 'disease'}, {text: 'Examination Date', value: 'examinationDate'}],
      results: [],
      search: "",
      perPage: 10,
      itemsLength: 0,
    }
  },

  methods: {
    getResults(pagination) {
      if (this.search) {
        ApiServices.getPatientResultsWithDisease((pagination) ? pagination - 1 : 0, this.search, {
          login: localStorage.getItem('login'),
          password: localStorage.getItem('password')
        }).then(response => {
          this.results = response.data.content;
          this.perPage = response.data.pageable.size;
          this.itemsLength = response.data.totalElements
        })
      } else {
        ApiServices.getPatientResults((pagination) ? pagination - 1 : 0, {
          login: localStorage.getItem('login'),
          password: localStorage.getItem('password')
        }).then(response => {
          this.results = response.data.content;
          this.perPage = response.data.pageable.size;
          this.itemsLength = response.data.totalElements
        })
      }
    },
    routeToResult(result) {
      this.$router.push({
        name: 'result', params: {id: result.id}
      });
    }
  },

  created() {
    this.getResults(1);
    this.debouncedGetAnswer = lodash.debounce(this.getResults, 500)
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
