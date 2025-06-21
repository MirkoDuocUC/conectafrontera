package cl.duoc.conectafrontera.service.impl;

import cl.duoc.conectafrontera.dto.ViajeroRequestDTO;
import cl.duoc.conectafrontera.dto.ViajeroResponseDTO;
import cl.duoc.conectafrontera.entity.Usuario;
import cl.duoc.conectafrontera.entity.Viajero;
import cl.duoc.conectafrontera.repository.UsuarioRepository;
import cl.duoc.conectafrontera.repository.ViajeroRepository;
import cl.duoc.conectafrontera.service.ViajeroService;
import cl.duoc.conectafrontera.util.RolUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ViajeroServiceImpl implements ViajeroService {

    private final UsuarioRepository usuarioRepository;
    private final ViajeroRepository viajeroRepository;

    @Override
    @Transactional
    public ViajeroResponseDTO registrarViajero(ViajeroRequestDTO dto) {
        // Validar si ya existe un usuario con ese correo
        Optional<Usuario> usuarioExistente = usuarioRepository.findByCorreo(dto.getCorreo());
        if (usuarioExistente.isPresent()) {
            throw new IllegalArgumentException("Ya existe un usuario con ese correo.");
        }

        // Crear nuevo usuario con rol VIAJERO
        Usuario nuevoUsuario = Usuario.builder().nombre(dto.getNombre()).correo(dto.getCorreo()).contrasena(dto.getContrasena()).rol(RolUsuario.VIAJERO).build();

        nuevoUsuario = usuarioRepository.save(nuevoUsuario);
        System.out.println("Usuario creado con ID: " + nuevoUsuario.getIdUsuario());

        // Crear Viajero sin setId manual — @MapsId se encarga
        Viajero viajero = Viajero.builder().usuario(nuevoUsuario).build();

        viajero = viajeroRepository.save(viajero);
        System.out.println("Viajero creado con ID: " + viajero.getId());

        return toDTO(viajero);
    }

    @Override
    public List<ViajeroResponseDTO> obtenerViajeros() {
        List<Viajero> viajeros = viajeroRepository.findAll();
        System.out.println("Total viajeros encontrados: " + viajeros.size());

        return viajeros.stream().map(this::toDTO).toList();
    }

    private ViajeroResponseDTO toDTO(Viajero viajero) {
        ViajeroResponseDTO dto = new ViajeroResponseDTO();
        dto.setIdUsuario(viajero.getId());
        dto.setNombre(viajero.getUsuario().getNombre());
        dto.setCorreo(viajero.getUsuario().getCorreo());
        return dto;
    }

    @Override
    @Transactional
    public ViajeroResponseDTO actualizarViajero(Long id, ViajeroRequestDTO dto) {
        Viajero viajero = viajeroRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Viajero no encontrado"));

        Usuario usuario = viajero.getUsuario();

        if (!usuario.getCorreo().equals(dto.getCorreo()) && usuarioRepository.findByCorreo(dto.getCorreo()).isPresent()) {
            throw new IllegalArgumentException("Correo ya en uso por otro usuario.");
        }

        usuario.setNombre(dto.getNombre());
        usuario.setCorreo(dto.getCorreo());
        usuario.setContrasena(dto.getContrasena());

        usuarioRepository.save(usuario);

        return toDTO(viajero);
    }

}
