<template>
  <div class="task-list-container">
    <div v-if="taskListData.error" class="error-message">
      {{ taskListData.error }}
    </div>
    <div v-else>
      <div v-for="task in taskListData.tasks" :key="task.id" class="task-item">
        <h3>{{ task.title }}</h3>
        <p>{{ task.description }}</p>
        <p><strong>Due Date:</strong> {{ task.dueDate }}</p>
        <p><strong>Status:</strong> {{ task.status }}</p>
        <button @click="deleteTask(task.id)" class="btn btn-danger">
          Delete
        </button>
        <span class="task-id">ID: {{ task.id }}</span>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted } from "vue";
import { useTaskList } from "@/composables/useTaskList";

export default defineComponent({
  name: "TaskList",
  setup() {
    const { taskListData, fetchTasks, deleteTask } = useTaskList();

    onMounted(() => {
      fetchTasks();
    });

    return {
      taskListData,
      fetchTasks,
      deleteTask,
    };
  },
});
</script>
