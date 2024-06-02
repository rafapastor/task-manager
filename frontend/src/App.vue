<template>
  <div id="app" class="container mx-auto">
    <TaskList />
  </div>
</template>

<script>
import TaskList from "./components/TaskList.vue";

export default {
  name: "App",
  components: {
    TaskList,
  },
};
</script>

<style>
/* Agrega tus estilos aquí si es necesario */
</style>
