export function touchForm() {
  let controls: any = document.getElementsByClassName("form-control");
  for (let i = 0; i < controls.length; i++) {
    let control: HTMLElement = controls.item(i);
    control.focus();
  }
  for (let i = 0; i < controls.length; i++) {
    let control: HTMLElement = controls.item(i);
    if (control.classList.contains("ng-invalid")) {
      control.focus();
      break;
    }
  }
}
