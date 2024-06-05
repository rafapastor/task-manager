<template>
  <div id="app" class="container mx-auto">
    <CreateTask @task-created="fetchTasks" />
    <TaskList ref="taskList" />
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import CreateTask from "./components/CreateTask.vue";
import TaskList from "./components/TaskList.vue";
import "./assets/tailwind.css";
import "./assets/styles.css";

export default defineComponent({
  name: "App",
  components: {
    CreateTask,
    TaskList,
  },
  setup() {
    const taskListRef = ref<InstanceType<typeof TaskList> | null>(null);

    const fetchTasks = () => {
      taskListRef.value?.fetchTasks();
    };

    return {
      taskListRef,
      fetchTasks,
    };
  },
});
</script>

<style>
/* Puedes agregar estilos globales aquí si lo deseas */
</style>
