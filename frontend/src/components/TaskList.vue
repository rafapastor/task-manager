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
        <div class="task-actions">
          <button @click="editTask(task)" class="task-btn task-btn-edit">
            Edit
          </button>
          <button @click="deleteTask(task.id)" class="task-btn task-btn-delete">
            Delete
          </button>
        </div>
        <span class="task-id">ID: {{ task.id }}</span>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted } from "vue";
import { useTaskList } from "@/composables/useTaskList";
import { Task } from "@/types/types";

export default defineComponent({
  name: "TaskList",
  emits: ["edit-task"],
  setup(_, { emit }) {
    const { taskListData, fetchTasks, deleteTask } = useTaskList();

    onMounted(() => {
      fetchTasks();
    });

    const editTask = (task: Task) => {
      emit("edit-task", task);
    };

    return {
      taskListData,
      fetchTasks,
      deleteTask,
      editTask,
    };
  },
});
</script>
