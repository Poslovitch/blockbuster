package mchorse.blockbuster.config;

import mchorse.blockbuster.Blockbuster;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Blockbuster config class
 *
 * This class stores config properties powered by Forge built-in configuration
 * library.
 *
 * I looked up how this configuration works in Minema mod, so don't wonder if
 * it looks pretty similar. I also looked up Chroonster TestMod3.
 */
public class BlockbusterConfig
{
    /* General */

    /**
     * Send all server models and skins to the player who's logging in?
     */
    public boolean load_models_on_login;

    /**
     * Remove all downloaded models after exiting a server?
     */
    public boolean clean_model_downloads;

    /**
     * Is teleport feature disabled when you sneak and using the playback button?
     */
    public boolean disable_teleport_playback_button;

    /**
     * This option does literally nothing. Or maybe it does...?
     */
    public boolean extra_wubs;

    /**
     * Refresh models and skins when entering in Metamorph or Blockbuster GUIs?
     */
    public boolean auto_refresh_models;

    /* Camera */

    /**
     * Camera duration step (used by keyboard duration bindings)
     */
    public int camera_duration_step;

    /**
     * Default camera duration (used by keyboard fixture bindings)
     */
    public int camera_duration;

    /**
     * Interpolate target fixtures position?
     */
    public boolean camera_interpolate_target;

    /**
     * Ratio for target fixtures interpolation
     */
    public float camera_interpolate_target_value;

    /**
     * Switch to spectator mode when starting camera playback?
     */
    public boolean camera_spectator;

    /**
     * Factor for step keys
     */
    public float camera_step_factor;

    /**
     * Factor for rotate keys
     */
    public float camera_rotate_factor;

    /**
     * Enable Minema recording on camera playback, and finish Minema recording
     * when camera finish to playback
     */
    public boolean camera_minema;

    /**
     * Clamp smooth camera's pitch between -90 and 90 degrees range?
     */
    public boolean camera_smooth_clamp;

    /**
     * Default camera path interpolation method
     */
    public String camera_path_default_interp;

    /* Recording */

    /**
     * Recording <s>final</s> countdown
     */
    public int recording_countdown;

    /**
     * Recording frame skip
     */
    public int recording_delay;

    /**
     * How long does it take to unload a record
     */
    public int record_unload_time;

    /**
     * Enable automatic record unloading?
     */
    public boolean record_unload;

    /**
     * How often a recording is going to synchronize with the server
     */
    public int record_sync_rate;

    /**
     * Does attack action get recorded with swipe action?
     */
    public boolean record_attack_on_swipe;

    /**
     * Does command action get recorded during recording?
     */
    public boolean record_commands;

    /* Actors */

    /**
     * Do actors receive fall damage?
     */
    public boolean actor_fall_damage;

    /**
     * Actor tracking range. Requires restart
     */
    public int actor_tracking_range;

    /**
     * Actor rendering range
     */
    public int actor_rendering_range;

    /**
     * Enable unconditional actor nametag rendering
     */
    public boolean actor_always_render_names;

    /* Damage control */

    /**
     * Whether damage control is active
     */
    public boolean damage_control;

    /**
     * Radius of effect for damage control
     */
    public int damage_control_distance;

    /* Non conifg option stuff */

    /**
     * Forge configuration
     */
    public Configuration config;

    public BlockbusterConfig(Configuration config)
    {
        this.config = config;
        this.reload();
    }

    /**
     * Reload config values
     *
     * Pretty dense code, am I right? Welcome to the Jungle, I guess.
     */
    public void reload()
    {
        String general = Configuration.CATEGORY_GENERAL;
        String recording = "recording";
        String camera = "camera";
        String actor = "actor";
        String damage = "damage_control";

        String genPrefix = "blockbuster.config.general.";
        String recPrefix = "blockbuster.config.recording.";
        String camPrefix = "blockbuster.config.camera.";
        String actPrefix = "blockbuster.config.actor.";
        String damPrefix = "blockbuster.config.damage_control.";

        /* General */
        this.load_models_on_login = this.config.getBoolean("load_models_on_login", general, false, "Send all server models and skins to the player who's logging in?", genPrefix + "load_models_on_login");
        this.clean_model_downloads = this.config.getBoolean("clean_model_downloads", general, true, "Remove all downloaded models after exiting a server?", genPrefix + "clean_model_downloads");
        this.disable_teleport_playback_button = this.config.getBoolean("disable_teleport_playback_button", general, false, "Is teleport feature disabled when you sneak and using the playback button?", genPrefix + "disable_teleport_playback_button");
        this.extra_wubs = this.config.getBoolean("extra_wubs", general, false, "This option does literally nothing. Or maybe it does...?", genPrefix + "extra_wubs");
        this.auto_refresh_models = this.config.getBoolean("auto_refresh_models", general, true, "Refresh models and skins when entering in Metamorph or Blockbuster GUIs?", genPrefix + "auto_refresh_models");

        /* Camera */
        this.camera_duration_step = this.config.getInt("camera_duration_step", camera, 10, 1, 100, "What is default step to use when adding or reducing duration of the camera fixture (in ticks)", camPrefix + "camera_duration_step");
        this.camera_duration = this.config.getInt("camera_duration", camera, 30, 1, 1000, "What is default duration of the camera fixture (in ticks)", camPrefix + "camera_duration");
        this.camera_interpolate_target = this.config.getBoolean("camera_interpolate_target", camera, false, "Interpolate target based camera fixtures (follow and look) outcome", camPrefix + "camera_interpolate_target");
        this.camera_interpolate_target_value = this.config.getFloat("camera_interpolate_target_value", camera, 0.5F, 0.0F, 1.0F, "Interpolation value for target based camera fixture interpolation", camPrefix + "camera_interpolate_target_value");
        this.camera_spectator = this.config.getBoolean("camera_spectator", camera, true, "Switch to spectator mode when starting camera playback?", camPrefix + "camera_spectator");
        this.camera_step_factor = this.config.getFloat("camera_step_factor", camera, 0.01F, 0, 10, "Camera step factor for step keys", camPrefix + "camera_step_factor");
        this.camera_rotate_factor = this.config.getFloat("camera_rotate_factor", camera, 0.1F, 0, 10, "Camera rotate factor for rotate keys", camPrefix + "camera_rotate_factor");
        this.camera_minema = this.config.getBoolean("camera_minema", camera, false, "Enable Minema recording on camera playback, and finish Minema recording when camera finish to playback", camPrefix + "camera_minema");
        this.camera_path_default_interp = this.config.getString("camera_path_default_interp", camera, "linear", "Default interpolation method for path fixture", camPrefix + "camera_path_default_interp");

        /* Smooth camera */
        this.camera_smooth_clamp = this.config.getBoolean("camera_smooth_clamp", "camera.smooth", true, "Clip smooth camera's pitch between -90 and 90 degrees range?", camPrefix + "camera_smooth_clamp");

        /* Recording */
        this.recording_countdown = this.config.getInt("recording_countdown", recording, 3, 0, 10, "Recording countdown (in seconds)", recPrefix + "recording_countdown");
        this.recording_delay = this.config.getInt("recording_delay", recording, 1, 1, 10, "Frame delay for recording", recPrefix + "recording_delay");
        this.record_unload_time = this.config.getInt("record_unload_time", recording, 2400, 600, 72000, "How long does it take to unload a record (in ticks)", recPrefix + "record_unload_time");
        this.record_unload = this.config.getBoolean("record_unload", recording, true, "Enable automatic record unloading?", recPrefix + "record_unload");
        this.record_sync_rate = this.config.getInt("record_sync_rate", recording, 6, 1, 30, "How often a recording is going to synchronize with the server", recPrefix + "record_sync_rate");
        this.record_attack_on_swipe = this.config.getBoolean("record_attack_on_swipe", recording, false, "Does attack action get recorded with swipe action?", recPrefix + "record_attack_on_swipe");
        this.record_commands = this.config.getBoolean("record_commands", recording, true, "Does command action get recorded during recording?", recPrefix + "record_commands");

        /* Actor */
        this.actor_fall_damage = this.config.getBoolean("actor_fall_damage", actor, true, "Do actors receive fall damage?", actPrefix + "actor_fall_damage");
        this.actor_tracking_range = this.config.getInt("actor_tracking_range", actor, 96, 64, 1024, "How far actors are tracked? Requires restart of the game.", actPrefix + "actor_tracking_range");
        this.actor_rendering_range = this.config.getInt("actor_rendering_range", actor, 64, 64, 1024, "How far actors are seen?", actPrefix + "actor_rendering_range");
        this.actor_always_render_names = this.config.getBoolean("actor_always_render_names", actor, false, "Enable unconditional actor nametag rendering", actPrefix);

        /* Damage control */
        this.damage_control = this.config.getBoolean("damage_control", damage, false, "Whether damage control is active", damPrefix + "damage_control");
        this.damage_control_distance = this.config.getInt("damage_control_distance", damage, 32, 1, 1024, "Radius of effect for damage control", damPrefix + "damage_control_distance");

        Blockbuster.proxy.onConfigChange(this.config);

        if (this.config.hasChanged())
        {
            this.config.save();
        }
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.getModID().equals(Blockbuster.MODID) && this.config.hasChanged())
        {
            this.reload();
        }
    }
}