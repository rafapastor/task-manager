<template>
  <div id="app" class="container mx-auto p-4">
    <button
      @click="openModal"
      class="mb-4 bg-green-500 text-white p-2 rounded-md hover:bg-green-700"
    >
      Create Task
    </button>
    <TaskList
      @edit-task="handleEditTask"
      @delete-task="handleDeleteTask"
      :task-list-data="taskListData"
    />

    <TaskModal :isOpen="isModalOpen" @close="closeModal" :title="modalTitle">
      <CreateTask @task-created="handleTaskCreated" :task="currentTask" />
    </TaskModal>
  </div>
</template>

<script lang="ts">
import { computed, defineComponent, onMounted, ref } from "vue";
import CreateTask from "./components/CreateTask.vue";
import TaskList from "./components/TaskList.vue";
import TaskModal from "./components/TaskModal.vue";
import { Task } from "@/types/types";
import "./assets/styles.css";
import { useTaskList } from "./composables/useTaskList";

export default defineComponent({
  name: "App",
  components: {
    CreateTask,
    TaskList,
    TaskModal,
  },
  setup() {
    const { taskListData, fetchTasks, deleteTask } = useTaskList();
    const isModalOpen = ref(false);
    const currentTask = ref<Task | null>(null);

    onMounted(() => {
      fetchTasks();
    });

    const openModal = () => {
      isModalOpen.value = true;
    };

    const closeModal = () => {
      isModalOpen.value = false;
      currentTask.value = null;
    };

    const handleTaskCreated = () => {
      closeModal();
      fetchTasks();
    };

    const handleEditTask = (task: Task) => {
      currentTask.value = { ...task };
      openModal();
    };

    const handleDeleteTask = async (task: Task) => {
      if (!task.id) return;
      await deleteTask(task.id);
      fetchTasks();
    };

    const modalTitle = computed(() => {
      return currentTask.value ? "Update Task" : "Create Task";
    });

    return {
      isModalOpen,
      openModal,
      closeModal,
      handleTaskCreated,
      handleEditTask,
      handleDeleteTask,
      currentTask,
      taskListData,
      modalTitle,
    };
  },
});
</script>
