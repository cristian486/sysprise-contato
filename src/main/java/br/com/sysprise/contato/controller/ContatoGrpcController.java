package br.com.sysprise.contato.controller;

import br.com.sysprise.contato.service.ContatoService;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import pb.Contato;
import pb.ContatoServiceGrpc;
import pb.ListaContatos;
import pb.PessoaId;

import java.util.List;

@GrpcService
@AllArgsConstructor
public class ContatoGrpcController extends ContatoServiceGrpc.ContatoServiceImplBase {

    private final ContatoService contatoService;

    @Override
    public void getContatoByPessoaId(PessoaId request, StreamObserver<ListaContatos> responseObserver) {
        List<Contato> listaContatosGrpc = contatoService.findAllByPessoaId(request.getId()).stream().map(c -> {
            return Contato.newBuilder()
                    .setEmail(c.getEmail())
                    .setTelefone(c.getTelefone())
                    .setId(c.getId())
                    .build();
        }).toList();
        ListaContatos listaContatos = ListaContatos.newBuilder().addAllContato(listaContatosGrpc).build();
        responseObserver.onNext(listaContatos);
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<pb.CriarContatoRequest> createContatos(StreamObserver<pb.blank> responseObserver) {
        return new StreamObserver<pb.CriarContatoRequest>() {
            @Override
            public void onNext(pb.CriarContatoRequest criarContatoRequest) {
                contatoService.cadastrar(criarContatoRequest);
            }

            @Override
            public void onError(Throwable throwable) {
                responseObserver.onError(throwable);
            }

            @Override
            public void onCompleted() {
                responseObserver.onNext(pb.blank.newBuilder().build());
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public void updateContato(Contato request, StreamObserver<pb.blank> responseObserver) {
        contatoService.atualizar(request);
        responseObserver.onNext(pb.blank.newBuilder().build());
        responseObserver.onCompleted();
    }
}
