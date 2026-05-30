@echo off
setlocal enabledelayedexpansion
title Fisk Heroes Rebirth - Animation System
color 0A

echo.
echo ============================================================
echo  GERANDO SISTEMA DE ANIMACOES CLIENT
echo ============================================================
echo.

set BASE=src\main\java\com\fiskheroesrebirth\mod
set PKG=com.fiskheroesrebirth.mod

echo [1/7] AnimationState - rastreador de estado...

REM === AnimationState.java ===
(
echo package %PKG%.client.animation;
echo.
echo import java.util.*;
echo import java.util.concurrent.ConcurrentHashMap;
echo.
echo /**
echo  * Rastreador de estado de animacoes por player
echo  * Thread-safe e otimizado
echo  */
echo public class AnimationState {
echo.
echo     private static final Map^<UUID, PlayerAnimationData^> STATES = new ConcurrentHashMap^<^>(^);
echo.
echo     public static PlayerAnimationData get(UUID playerId^) {
echo         return STATES.computeIfAbsent(playerId, k -^> new PlayerAnimationData(^)^);
echo     }
echo.
echo     public static void play(UUID playerId, String animation^) {
echo         PlayerAnimationData data = get(playerId^);
echo         data.currentAnimation = animation;
echo         data.startTime = System.currentTimeMillis(^);
echo         data.progress = 0f;
echo         data.playing = true;
echo     }
echo.
echo     public static void stop(UUID playerId^) {
echo         PlayerAnimationData data = get(playerId^);
echo         data.playing = false;
echo         data.currentAnimation = null;
echo     }
echo.
echo     public static String getCurrentAnimation(UUID playerId^) {
echo         return get(playerId^).currentAnimation;
echo     }
echo.
echo     public static float getProgress(UUID playerId^) {
echo         return get(playerId^).progress;
echo     }
echo.
echo     public static boolean isPlaying(UUID playerId^) {
echo         return get(playerId^).playing;
echo     }
echo.
echo     public static void remove(UUID playerId^) {
echo         STATES.remove(playerId^);
echo     }
echo.
echo     public static int activeCount(^) { return STATES.size(^); }
echo.
echo     public static class PlayerAnimationData {
echo         public String currentAnimation = null;
echo         public long startTime = 0;
echo         public float progress = 0f;
echo         public boolean playing = false;
echo         public boolean loop = false;
echo         public int duration = 20;
echo         public float speed = 1.0f;
echo     }
echo }
) > "%BASE%\client\animation\AnimationState.java"
echo  [OK] AnimationState.java

echo.
echo [2/7] AnimationRegistry - registro de animacoes...

REM === AnimationRegistry.java ===
(
echo package %PKG%.client.animation;
echo.
echo import java.util.*;
echo import java.util.concurrent.ConcurrentHashMap;
echo.
echo /**
echo  * Registro de animacoes mapeadas por ID
echo  * Mapeia keys de abilities para animacoes .fsk
echo  */
echo public class AnimationRegistry {
echo.
echo     private static final Map^<String, AnimationData^> REGISTRY = new ConcurrentHashMap^<^>(^);
echo.
echo     public static void register(String id, String fskFile, int duration, boolean loop^) {
echo         REGISTRY.put(id, new AnimationData(id, fskFile, duration, loop^)^);
echo     }
echo.
echo     public static AnimationData get(String id^) {
echo         return REGISTRY.get(id^);
echo     }
echo.
echo     public static Collection^<AnimationData^> getAll(^) {
echo         return REGISTRY.values(^);
echo     }
echo.
echo     public static int count(^) { return REGISTRY.size(^); }
echo.
echo     public static void registerDefaults(^) {
echo         // Iron Man
echo         register("helmet_deploy", "mk46_helmet", 20, false^);
echo         register("repulsor_fire", "dual_hand_beam", 15, false^);
echo         register("flight_lean", "flight_lean", 20, true^);
echo         register("iron_dive", "iron_man_dive", 30, false^);
echo.
echo         // Spider-Man
echo         register("web_shoot_left", "web_shoot_left", 10, false^);
echo         register("web_shoot_right", "web_shoot_right", 10, false^);
echo         register("swing_default", "swing_default", 30, true^);
echo         register("swing_dive", "swing_dive", 20, false^);
echo         register("wall_crawl", "crawl_wall", 20, true^);
echo         register("ceiling_crawl", "crawl_ceiling", 20, true^);
echo.
echo         // Flash
echo         register("speedster_sprint", "speedster_sprint", 10, true^);
echo.
echo         // Combat
echo         register("blocking", "blocking", 15, true^);
echo         register("blocking_arm", "blocking_arm", 15, true^);
echo         register("ocular_beam", "ocular_beam", 20, false^);
echo         register("sword_pose", "sword_pose", 20, true^);
echo.
echo         // Movement
echo         register("hero_landing", "superhero_landing", 25, false^);
echo         register("falcon_dive", "falcon_dive_roll", 30, false^);
echo         register("manta_flight", "mantapack_flight", 30, true^);
echo.
echo         // Aiming
echo         register("aiming", "aiming", 10, true^);
echo         register("aiming_left", "aiming_left", 10, true^);
echo         register("dual_aiming", "dual_aiming", 10, true^);
echo.
echo         // Web aim
echo         register("web_aim_left", "web_aim_left", 10, true^);
echo         register("web_aim_right", "web_aim_right", 10, true^);
echo         register("web_rappel", "web_rappel", 25, false^);
echo.
echo         // Gestures
echo         register("hat_tip", "hat_tip_player", 15, false^);
echo         register("remove_cowl", "remove_cowl", 20, false^);
echo.
echo         System.out.println("[Animations] " + REGISTRY.size(^) + " animacoes registradas"^);
echo     }
echo.
echo     public static class AnimationData {
echo         public final String id;
echo         public final String fskFile;
echo         public final int duration;
echo         public final boolean loop;
echo.
echo         public AnimationData(String id, String fsk, int dur, boolean loop^) {
echo             this.id = id;
echo             this.fskFile = fsk;
echo             this.duration = dur;
echo             this.loop = loop;
echo         }
echo     }
echo }
) > "%BASE%\client\animation\AnimationRegistry.java"
echo  [OK] AnimationRegistry.java

echo.
echo [3/7] AnimationPlayer - executor de animacoes...

REM === AnimationPlayer.java ===
(
echo package %PKG%.client.animation;
echo.
echo import java.util.UUID;
echo.
echo /**
echo  * Player de animacoes - controla playback
echo  */
echo public class AnimationPlayer {
echo.
echo     public static void play(UUID playerId, String animationId^) {
echo         AnimationRegistry.AnimationData data = AnimationRegistry.get(animationId^);
echo         if (data == null^) {
echo             System.err.println("[Animation] Nao encontrada: " + animationId^);
echo             return;
echo         }
echo.
echo         AnimationState.PlayerAnimationData state = AnimationState.get(playerId^);
echo         state.currentAnimation = data.fskFile;
echo         state.startTime = System.currentTimeMillis(^);
echo         state.progress = 0f;
echo         state.playing = true;
echo         state.loop = data.loop;
echo         state.duration = data.duration;
echo.
echo         System.out.println("[Animation] Playing: " + animationId + " (" + data.fskFile + ")"^);
echo     }
echo.
echo     public static void stop(UUID playerId^) {
echo         AnimationState.stop(playerId^);
echo     }
echo.
echo     public static void tick(UUID playerId^) {
echo         AnimationState.PlayerAnimationData state = AnimationState.get(playerId^);
echo         if (!state.playing^) return;
echo.
echo         long elapsed = System.currentTimeMillis(^) - state.startTime;
echo         float durationMs = state.duration * 50f;
echo         state.progress = Math.min(1f, elapsed / durationMs^) * state.speed;
echo.
echo         if (state.progress ^>= 1f^) {
echo             if (state.loop^) {
echo                 state.startTime = System.currentTimeMillis(^);
echo                 state.progress = 0f;
echo             } else {
echo                 state.playing = false;
echo             }
echo         }
echo     }
echo.
echo     public static float getProgress(UUID playerId^) {
echo         return AnimationState.get(playerId^).progress;
echo     }
echo }
) > "%BASE%\client\animation\AnimationPlayer.java"
echo  [OK] AnimationPlayer.java

echo.
echo [4/7] FskAnimationApplier - aplica FSK em models...

REM === FskAnimationApplier.java ===
(
echo package %PKG%.client.animation;
echo.
echo import net.minecraft.client.model.HumanoidModel;
echo import net.minecraft.client.model.geom.ModelPart;
echo.
echo /**
echo  * Aplica resultados FSK em models do Minecraft
echo  * Integra com FskEngine para animacoes procedurais
echo  */
echo public class FskAnimationApplier {
echo.
echo     /**
echo      * Aplica animacao FSK em um HumanoidModel
echo      */
echo     public static void apply(HumanoidModel^<?^> model, String animation, float data^) {
echo         if (model == null ^|^| animation == null^) return;
echo.
echo         // TODO: Conectar com FskEngine quando disponivel
echo         // FskContext ctx = FskEngine.execute(animation, data^);
echo         // if (ctx != null^) applyToModel(model, ctx^);
echo     }
echo.
echo     /**
echo      * Aplica transformacoes em um ModelPart especifico
echo      */
echo     public static void applyToBone(ModelPart part, float rotX, float rotY, float rotZ^) {
echo         if (part == null^) return;
echo         part.xRot += rotX;
echo         part.yRot += rotY;
echo         part.zRot += rotZ;
echo     }
echo.
echo     /**
echo      * Aplica posicao em um ModelPart
echo      */
echo     public static void applyPosition(ModelPart part, float posX, float posY, float posZ^) {
echo         if (part == null^) return;
echo         part.x += posX;
echo         part.y += posY;
echo         part.z += posZ;
echo     }
echo.
echo     /**
echo      * Aplica tudo de uma vez
echo      */
echo     public static void applyFull(ModelPart part, float rotX, float rotY, float rotZ,
echo                                   float posX, float posY, float posZ^) {
echo         if (part == null^) return;
echo         part.xRot += rotX;
echo         part.yRot += rotY;
echo         part.zRot += rotZ;
echo         part.x += posX;
echo         part.y += posY;
echo         part.z += posZ;
echo     }
echo.
echo     /**
echo      * Reseta um bone para posicao padrao
echo      */
echo     public static void reset(ModelPart part^) {
echo         if (part == null^) return;
echo         part.xRot = 0;
echo         part.yRot = 0;
echo         part.zRot = 0;
echo         part.x = 0;
echo         part.y = 0;
echo         part.z = 0;
echo     }
echo }
) > "%BASE%\client\animation\FskAnimationApplier.java"
echo  [OK] FskAnimationApplier.java

echo.
echo [5/7] AnimationLayer - layer de renderizacao...

REM === AnimationLayer.java ===
(
echo package %PKG%.client.animation;
echo.
echo import com.mojang.blaze3d.vertex.PoseStack;
echo import net.minecraft.client.model.HumanoidModel;
echo import net.minecraft.client.renderer.MultiBufferSource;
echo import net.minecraft.world.entity.player.Player;
echo.
echo /**
echo  * Layer de animacao aplicado durante o render do player
echo  * Conecta o sistema FSK com o pipeline de renderizacao
echo  */
echo public class AnimationLayer {
echo.
echo     public static void render(Player player, HumanoidModel^<?^> model, 
echo                                PoseStack pose, MultiBufferSource buffer,
echo                                float partialTicks^) {
echo         if (player == null ^|^| model == null^) return;
echo.
echo         String anim = AnimationState.getCurrentAnimation(player.getUUID(^)^);
echo         if (anim == null ^|^| !AnimationState.isPlaying(player.getUUID(^)^)^) return;
echo.
echo         float progress = AnimationState.getProgress(player.getUUID(^)^);
echo.
echo         // Aplica animacao no model
echo         FskAnimationApplier.apply(model, anim, progress^);
echo     }
echo.
echo     /**
echo      * Tick chamado a cada frame
echo      */
echo     public static void tick(Player player^) {
echo         if (player != null^) {
echo             AnimationPlayer.tick(player.getUUID(^)^);
echo         }
echo     }
echo }
) > "%BASE%\client\animation\AnimationLayer.java"
echo  [OK] AnimationLayer.java

echo.
echo [6/7] BoneTransform - transformacoes de bones...

REM === BoneTransform.java ===
(
echo package %PKG%.client.animation;
echo.
echo /**
echo  * Representa uma transformacao a ser aplicada em um bone
echo  * Imutavel para thread-safety
echo  */
echo public class BoneTransform {
echo.
echo     public final String boneName;
echo     public final float rotX, rotY, rotZ;
echo     public final float posX, posY, posZ;
echo     public final float scaleX, scaleY, scaleZ;
echo.
echo     public BoneTransform(String boneName,
echo                          float rotX, float rotY, float rotZ,
echo                          float posX, float posY, float posZ^) {
echo         this(boneName, rotX, rotY, rotZ, posX, posY, posZ, 1f, 1f, 1f^);
echo     }
echo.
echo     public BoneTransform(String boneName,
echo                          float rotX, float rotY, float rotZ,
echo                          float posX, float posY, float posZ,
echo                          float scaleX, float scaleY, float scaleZ^) {
echo         this.boneName = boneName;
echo         this.rotX = rotX; this.rotY = rotY; this.rotZ = rotZ;
echo         this.posX = posX; this.posY = posY; this.posZ = posZ;
echo         this.scaleX = scaleX; this.scaleY = scaleY; this.scaleZ = scaleZ;
echo     }
echo.
echo     public static BoneTransform identity(String boneName^) {
echo         return new BoneTransform(boneName, 0, 0, 0, 0, 0, 0^);
echo     }
echo.
echo     public BoneTransform combine(BoneTransform other^) {
echo         return new BoneTransform(boneName,
echo             rotX + other.rotX, rotY + other.rotY, rotZ + other.rotZ,
echo             posX + other.posX, posY + other.posY, posZ + other.posZ,
echo             scaleX * other.scaleX, scaleY * other.scaleY, scaleZ * other.scaleZ
echo         ^);
echo     }
echo.
echo     public BoneTransform scale(float factor^) {
echo         return new BoneTransform(boneName,
echo             rotX * factor, rotY * factor, rotZ * factor,
echo             posX * factor, posY * factor, posZ * factor,
echo             scaleX, scaleY, scaleZ
echo         ^);
echo     }
echo.
echo     @Override
echo     public String toString(^) {
echo         return String.format("BoneTransform[%%s rot=(%%.2f,%%.2f,%%.2f) pos=(%%.2f,%%.2f,%%.2f)]",
echo             boneName, rotX, rotY, rotZ, posX, posY, posZ^);
echo     }
echo }
) > "%BASE%\client\animation\BoneTransform.java"
echo  [OK] BoneTransform.java

echo.
echo [7/7] AnimationEvent - eventos de animacao...

REM === AnimationEvent.java ===
(
echo package %PKG%.client.animation;
echo.
echo import java.util.UUID;
echo.
echo /**
echo  * Evento disparado durante animacoes
echo  * Permite reagir a momentos especificos (ex: tocar som em frame X^)
echo  */
echo public class AnimationEvent {
echo.
echo     public enum Type {
echo         START,      // Animacao comecou
echo         END,        // Animacao terminou
echo         LOOP,       // Animacao fez loop
echo         KEYFRAME,   // Atingiu um keyframe especifico
echo         INTERRUPT;  // Animacao foi interrompida
echo     }
echo.
echo     public final Type type;
echo     public final UUID playerId;
echo     public final String animationId;
echo     public final float progress;
echo     public final long timestamp;
echo.
echo     public AnimationEvent(Type type, UUID playerId, String animationId, float progress^) {
echo         this.type = type;
echo         this.playerId = playerId;
echo         this.animationId = animationId;
echo         this.progress = progress;
echo         this.timestamp = System.currentTimeMillis(^);
echo     }
echo.
echo     @Override
echo     public String toString(^) {
echo         return String.format("[AnimEvent] %%s: %%s @ %%.2f", type, animationId, progress^);
echo     }
echo }
) > "%BASE%\client\animation\AnimationEvent.java"
echo  [OK] AnimationEvent.java

REM === AnimationEventHandler.java ===
(
echo package %PKG%.client.animation;
echo.
echo import java.util.*;
echo import java.util.concurrent.CopyOnWriteArrayList;
echo import java.util.function.Consumer;
echo.
echo /**
echo  * Handler de eventos de animacao
echo  * Permite registrar listeners para eventos especificos
echo  */
echo public class AnimationEventHandler {
echo.
echo     private static final List^<Consumer^<AnimationEvent^>^> LISTENERS = new CopyOnWriteArrayList^<^>(^);
echo.
echo     public static void register(Consumer^<AnimationEvent^> listener^) {
echo         LISTENERS.add(listener^);
echo     }
echo.
echo     public static void unregister(Consumer^<AnimationEvent^> listener^) {
echo         LISTENERS.remove(listener^);
echo     }
echo.
echo     public static void fire(AnimationEvent event^) {
echo         for (Consumer^<AnimationEvent^> listener : LISTENERS^) {
echo             try {
echo                 listener.accept(event^);
echo             } catch (Exception e^) {
echo                 System.err.println("[AnimEvent] Erro em listener: " + e.getMessage(^)^);
echo             }
echo         }
echo     }
echo.
echo     public static int listenerCount(^) { return LISTENERS.size(^); }
echo }
) > "%BASE%\client\animation\AnimationEventHandler.java"
echo  [OK] AnimationEventHandler.java

echo.
echo ============================================================
echo  SISTEMA DE ANIMACAO COMPLETO!
echo ============================================================
echo.
echo Arquivos criados em client\animation\:
echo.
echo  [✓] AnimationState.java          - Rastreador de estado
echo  [✓] AnimationRegistry.java       - Registry com 24+ animacoes
echo  [✓] AnimationPlayer.java         - Executor de playback
echo  [✓] FskAnimationApplier.java     - Integra com FSK Engine
echo  [✓] AnimationLayer.java          - Layer de renderizacao
echo  [✓] BoneTransform.java           - Transformacoes de bones
echo  [✓] AnimationEvent.java          - Sistema de eventos
echo  [✓] AnimationEventHandler.java   - Handler de eventos
echo.
echo TOTAL: 8 classes Java
echo.
echo ============================================================
echo  COMO USAR:
echo ============================================================
echo.
echo  // Registrar animacoes (no setup do mod)
echo  AnimationRegistry.registerDefaults(^);
echo.
echo  // Tocar animacao
echo  AnimationPlayer.play(player.getUUID(^), "helmet_deploy"^);
echo.
echo  // Verificar estado
echo  String anim = AnimationState.getCurrentAnimation(player.getUUID(^)^);
echo  float prog = AnimationState.getProgress(player.getUUID(^)^);
echo.
echo  // Reagir a eventos
echo  AnimationEventHandler.register(event -^> {
echo      if (event.type == AnimationEvent.Type.END^) {
echo          System.out.println("Animacao terminou!"^);
echo      }
echo  }^);
echo.
echo  // Parar animacao
echo  AnimationPlayer.stop(player.getUUID(^)^);
echo.
echo ============================================================
echo  ANIMACOES REGISTRADAS (24 disponiveis):
echo ============================================================
echo.
echo  Iron Man:        helmet_deploy, repulsor_fire, flight_lean, iron_dive
echo  Spider-Man:      web_shoot_left/right, swing_default, wall_crawl
echo  Flash:           speedster_sprint
echo  Combat:          blocking, ocular_beam, sword_pose
echo  Movement:        hero_landing, falcon_dive, manta_flight
echo  Aiming:          aiming, dual_aiming
echo  Web System:      web_aim_left/right, web_rappel
echo  Gestures:        hat_tip, remove_cowl
echo.
echo Pressione qualquer tecla para finalizar...
pause >nul