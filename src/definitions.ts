declare module "@capacitor/core" {
  interface PluginRegistry {
    CapacitorFileChooser: CapacitorFileChooserPlugin;
  }
}

export interface CapacitorFileChooserPlugin {
  echo(options: { value: string }): Promise<{value: string}>;
}
