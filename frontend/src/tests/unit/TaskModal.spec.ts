import { shallowMount } from "@vue/test-utils";
import TaskModal from "@/components/TaskModal.vue";

describe("TaskModal.vue", () => {
  const wrapper = shallowMount(TaskModal, {
    props: {
      isOpen: true,
      title: "Modal Title",
    },
  });

  it("renders modal with task details", () => {
    expect(wrapper.find(".modal-title").text()).toBe("Modal Title");
  });

  it("emits an event when close button is clicked", async () => {
    await wrapper.find(".modal-close").trigger("click");
    expect(wrapper.emitted().close).toBeTruthy();
  });
});
