import Patient from "./components/Patient";
import PatientAdd from "./components/PatientAdd";
import Result from "./components/Result";
import Results from "./components/Results";
import Doctor from "./components/Doctor";
import Login from "./components/Login";
import ResultAdd from "@/components/ResultAdd";
import MedicineAdd from "@/components/MedicineAdd";
import Medicines from "@/components/Medicines";

export const routes = [
    {path: '/login', component: Login, name:'login', meta: {title: 'MedicMongo — Login'}},
    {path: '/results', component: Results, name: 'results', meta: {title: 'MedicMongo — Results'}},
    {path: '/result/add', component: ResultAdd, name: 'result-add', meta: {title: 'MedicMongo — Add result'}},
    {path: '/result/:id', component: Result, name: 'result', meta: {title: 'MedicMongo — Details'}},
    {path: '/doctor/:id', component: Doctor, name:'doctor', meta: {title: 'MedicMongo — Doctor profile'}},
    {path: '/medicines', component: Medicines, name:'medicines', meta: {title: 'MedicMongo — Medicines'}},
    {path: '/medicine/:id', component: MedicineAdd, name:'medicine', meta: {title: 'MedicMongo — Medicine'}},
    {path: '/medicine/add', component: MedicineAdd, name:'medicine-add', meta: {title: 'MedicMongo — Medicine'}},
    {path: '/patient/add', component: PatientAdd, name: 'patient-add', meta: {title: 'MedicMongo — Add Patient'}},
    {path: '/patient/:id', component: Patient, name: 'patient', meta: {title: 'MedicMongo — Patient'}},
    {path: "*", redirect: "/login"}
]
