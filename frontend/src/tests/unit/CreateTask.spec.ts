import { shallowMount } from "@vue/test-utils";
import CreateTask from "@/components/CreateTask.vue";

describe("CreateTask.vue", () => {
  it("renders a form", () => {
    const wrapper = shallowMount(CreateTask);
    expect(wrapper.find("form").exists()).toBe(true);
  });

  it("emits an event when form is submitted", async () => {
    const wrapper = shallowMount(CreateTask);
    const input = wrapper.find("input");
    await input.setValue("New Task");
    await wrapper.find("form").trigger("submit.prevent");
    expect(wrapper.emitted().submit).toBeTruthy();
    expect(wrapper.emitted().submit[0]).toEqual([{ title: "New Task" }]);
  });
});
