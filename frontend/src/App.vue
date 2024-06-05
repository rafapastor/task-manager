<template>
  <div id="app" class="container mx-auto p-4">
    <button
      @click="openModal"
      class="mb-4 bg-green-500 text-white p-2 rounded-md hover:bg-green-700"
    >
      Create Task
    </button>
    <TaskList ref="taskList" @edit-task="handleEditTask" />

    <TaskModal :isOpen="isModalOpen" @close="closeModal" title="Create Task">
      <CreateTask @task-created="handleTaskCreated" :task="currentTask" />
    </TaskModal>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import CreateTask from "./components/CreateTask.vue";
import TaskList from "./components/TaskList.vue";
import TaskModal from "./components/TaskModal.vue";
import { Task } from "@/types/types";
import "./assets/tailwind.css";
import "./assets/styles.css";

export default defineComponent({
  name: "App",
  components: {
    CreateTask,
    TaskList,
    TaskModal,
  },
  setup() {
    const isModalOpen = ref(false);
    const taskListRef = ref<InstanceType<typeof TaskList> | null>(null);
    const currentTask = ref<Task | null>(null);

    const openModal = () => {
      isModalOpen.value = true;
    };

    const closeModal = () => {
      isModalOpen.value = false;
      currentTask.value = null;
    };

    const handleTaskCreated = () => {
      closeModal();
      if (taskListRef.value) {
        taskListRef.value.fetchTasks();
      }
    };

    const handleEditTask = (task: Task) => {
      currentTask.value = { ...task };
      openModal();
    };

    return {
      isModalOpen,
      taskListRef,
      openModal,
      closeModal,
      handleTaskCreated,
      handleEditTask,
      currentTask,
    };
  },
});
</script>
