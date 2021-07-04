<template>
  <div>
    <NavBar></NavBar>
    <div class="p-3 my-3 text-50 shadow-sm">
      <h2>Sign in</h2>
    </div>

    <div class="container">
      <v-form
          ref="form"
          @submit.prevent="loginApi"
      >
        <div class="d-flex justify-content-center">
          <b-form-input type="email"
                        v-model="login"
                        placeholder="Login"
                        class="w-50 m-2 center"
                        id="Login">

          </b-form-input>
        </div>

        <div class="d-flex justify-content-center">
          <b-form-input type="password"
                        v-model="password"
                        placeholder="Password"
                        class="w-50 m-2 center"
                        id="Password">

          </b-form-input>
        </div>

        <div class="d-flex justify-content-center">
          <v-btn
              class="m-2 float-left"
              :loading="loading"
              :disabled="loading"
              type="submit"
          >
            Login
          </v-btn>
        </div>
      </v-form>
    </div>

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
  </div>

</template>

<script>
import ApiServices from '../api-services';
import NavBar from "./NavBar";

export default {
  components: {NavBar},
  data() {
    return {
      login: '',
      password: '',
      loading: false,
      snackbar: false,
      snackbarText: ''
    }
  },

  methods: {
    loginApi() {
      this.loading = true;
      ApiServices.login({login: this.login, password: this.password}).then(response => {
        localStorage.setItem('role', response.data.role)
        localStorage.setItem('id', response.data.id)
        localStorage.setItem('login', this.login);
        localStorage.setItem('password', this.password);
        if (response.data.role === "PATIENT") {
          this.$router.push({
            name: 'results',
          });
        }
        if (response.data.role === "DOCTOR") {
          this.$router.push({
            name: 'result-add',
          });
        }

        this.loading = false;
      }, () => {
        this.snackbarText = 'Invalid login or password';
        this.snackbar = true;
        this.loading = false;
      });
    },
  },

  mounted() {
  }

}
</script>

<style scoped>

.center {
  margin-right: auto;
  margin-left: auto;
}

</style>
