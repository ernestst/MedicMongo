import axios from './axios.js';

class ApiServices {

    //result
    static getResults = (page) => axios.get('pokemon?page=' + page);

    static findResultById = (id) => axios.get('results/' + id);

    static getPatientResults = (page, user) => axios.get('results/all/patient?page=' + page, {auth: {username: user.login, password: user.password}});

    static getPatientResultsWithDisease = (page, d, user) => axios.get('results/all/patient/name?page=' + page + "&d=" + d, {auth: {username: user.login, password: user.password}});

    static addResult = (result) => axios.post('results/add', result);

    static deleteResult = (id) => axios.delete('results/remove/' + id);

    //doctor
    static findDoctorById = (id) => axios.get('doctors/' + id);

    static getLoggedDoctor = (user) => axios.get('doctors/me', {auth: {username: user.login, password: user.password}});

    static findByNameSurname = (page, name, surname) => axios.get('doctors/find?page=' + page + "&n=" + name + "&s=" + surname);

    //patient
    static findPatientById = (id) => axios.get('patients/' + id);

    static findPatientByPesel = (page, pesel) => axios.get('patients/find?page=' + page + '&p=' + pesel);

    static getAllPatients = () => axios.get('patients/all/list');

    static addPatient = (patient) => axios.post('patients/add', patient);

    //medicine
    static getAllMedicinesList = () => axios.get('medicines/all/list');

    static getAllMedicines = (page) => axios.get('medicines/all?page=' + page);

    static findMedicines = (page, name) => axios.get('medicines/find?page=' + page + '&n=' + name);

    static getMedicine = (id) => axios.get('medicines?id=' + id);

    static deleteMedicine = (id) => axios.delete('medicines/remove/' + id);

    static addMedicine = (medicine) => axios.post('medicines/add', medicine);

    static updateMedicine = (medicine) => axios.put('medicines/update', medicine);

    //user

    static login = (user) => axios.get('patients/login', {auth: {username: user.login, password: user.password}});

}

export default ApiServices;
